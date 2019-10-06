package com.king.webmagic.test;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * @ProjectName: crawlerfirst
 * @Package: com.king.webmagic.test
 * @ClassName: JobProcessor
 * @Author: 王团结
 * @Description:
 * @Date: 2019/10/5 10:39
 * @Version: 1.0
 */
public class JobProcessor implements PageProcessor {



    /**
     * 解析页面
     * @param page page
     */
    @Override
    public void process(Page page) {
        //解析返回的数据page，并且把解析的结果放到ResultItems中
        //css选择器
        page.putField("div",page.getHtml().css("div.w a").all());

        //XPath
        page.putField("div2",page.getHtml().xpath("//div[@class=w]/ul/li/a"));

        //正则表达式
        page.putField("div3",page.getHtml().css("div#news_div a").regex(".*江苏.*").all());

        //处理结果API
        page.putField("div4",page.getHtml().css("div#news_div a").regex(".*江苏.*").get());
        page.putField("div5",page.getHtml().css("div#news_div a").regex(".*江苏.*").toString());

        //获取连接
        page.addTargetRequests(page.getHtml().css("div#news_div").links().regex(".*9$").all());
        page.putField("url",page.getHtml().css("div.mt h1").all());
    }

    private Site site=Site.me()
            .setCharset("UTF-8")//编码
            .setSleepTime(1)//抓取间隔时间
            .setTimeOut(1000*10)//超时时间，单位ms毫秒
            .setRetrySleepTime(3000)//重试的间隔时间，单位ms毫秒
            .setRetryTimes(3);//重试次数
    /**
     *
     * @return
     */
    @Override
    public Site getSite() {
        return site;
    }

    //主函数，执行爬虫
    public static void main(String[] args) {
        Spider.create(new JobProcessor())
                .addUrl("https://www.jd.com/moreSubject.aspx")  //设置爬取数据的页面
                .addPipeline(new FilePipeline("D:\\Project\\frame\\crawl\\crawler-webmagic\\src\\main\\resources\\results\\"))
                .thread(5)      //设置有5个线程处理
                .run();     //执行爬虫
    }
}
