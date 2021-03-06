package com.king.job.task;

import com.king.job.domain.JobInfo;
import com.king.job.utils.MathSalary;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.BloomFilterDuplicateRemover;
import us.codecraft.webmagic.scheduler.QueueScheduler;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import java.util.List;

/**
 * @ProjectName: crawlerfirst
 * @Package: com.king.job.task
 * @ClassName: JobProcessor
 * @Author: 王团结
 * @Description:
 * @Date: 2019/10/5 16:45
 * @Version: 1.0
 */
@Component
public class JobProcessor implements PageProcessor {

    private String url = "https://search.51job.com/list/000000,000000,0000,32%252C01,9,99,java,2,1.html" +
            "?lang=c&stype=&postchannel=0000&workyear=99&cotype=99&degreefrom=99&jobterm=99" +
            "&companysize=99&providesalary=99&lonlat=0%2C0&radius=-1&ord_field=0&confirmdate=9" +
            "&fromType=&dibiaoid=0&address=&line=&specialarea=00&from=&welfare=";

    @Override
    public void process(Page page) {
        //解析页面，获取招聘信息详情的url地址
        List<Selectable> list = page.getHtml().css("div#resultList div.el").nodes();

        //判断获取到的集合是否为空
        if (list.size()==0) {
            //如果为空表示这是招聘详情页，解析页面，获取招聘详情信息，保存数据
            savaJobInfo(page);
        }else {
            //如果不为空，表示这是列表页,解析详情页的url地址，放到任务队列中
            for (Selectable selectable : list) {
                //获取url地址
                String jobInfoUrl = selectable.links().toString();
                //把获取到的url地址放到任务队列中
                page.addTargetRequest(jobInfoUrl);
            }

            //获取下一页的url
            String bkUrl = page.getHtml().css("div.p_in li.bk").nodes().get(1).links().toString();
            //把url放到任务队列中
            page.addTargetRequest(bkUrl);
        }
        String html = page.getHtml().toString();

    }

    /**
     * 解析页面，获取招聘详情信息，保存数据
     * @param page
     */
    private void savaJobInfo(Page page) {
        //创建招聘详情对象
        JobInfo jobInfo = new JobInfo();

        //解析页面
        Html html = page.getHtml();

        //获取数据，封装到对象中
        jobInfo.setCompanyName(html.css("div.cn p.cname a", "text").toString());
        jobInfo.setCompanyAddr(Jsoup.parse(html.css("div[class=bmsg inbox] > p").toString()).text());
        jobInfo.setCompanyInfo(Jsoup.parse(html.css("div.tmsg").nodes().toString()).text());
        jobInfo.setCompanyUrl(html.css("div.cn p.cname a").links().toString());
        jobInfo.setJobName(html.css("div.cn h1", "text").toString());
        jobInfo.setJobAddr(html.css("div.cn p.msg").regex("title=\"(.*?)&nbsp;").toString());
        jobInfo.setJobInfo(Jsoup.parse(html.css("div.job_msg").toString()).text());
        jobInfo.setJobUrl(page.getUrl().toString());


        //获取薪资
        Integer[] salary = MathSalary.getSalary(html.css("div.cn strong", "text").toString());
        jobInfo.setSalaryMax(salary[1]);
        jobInfo.setSalaryMin(salary[0]);

        //获取发布时间
        jobInfo.setTime(html.css("div.cn p.msg").regex("(([0-9]+\\-[0-9]+))发布").toString());

        //把结果保存起来
        page.putField("jobInfo",jobInfo);

    }

    @Override
    public Site getSite() {
        return Site.me()
                .setCharset("gbk")    //设置编码
                .setTimeOut(10*1000)    //设置超时时间
                .setRetrySleepTime(3*1000)  //设置重试的间隔时间
                .setRetryTimes(3);      //设置重试的次数
    }

    @Autowired
    private SpringDataPipeline springDataPipeline;
    /**
     * initialDelay当任务启动后，等待多久执行方法
     * fixedDelay每隔多久执行方法
     */
    //@Scheduled(initialDelay = 1000, fixedDelay = 100 *1000)
    public void process() {
        Spider.create(new JobProcessor())
                .addUrl(url)
                .setScheduler(new QueueScheduler().setDuplicateRemover(new BloomFilterDuplicateRemover(10*1000)))
                .thread(10)
                .addPipeline(springDataPipeline)
                .run();
    }
}
