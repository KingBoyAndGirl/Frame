package com.king.job.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.proxy.Proxy;
import us.codecraft.webmagic.proxy.SimpleProxyProvider;

/**
 * @ProjectName: crawlerfirst
 * @Package: com.king.job.task
 * @ClassName: ProxyTest
 * @Author: 王团结
 * @Description:
 * @Date: 2019/10/5 22:28
 * @Version: 1.0
 */
@Component
public class ProxyTest implements PageProcessor {

    //@Scheduled(fixedDelay = 10000)
    public void testProxy() {
        //创建下载器Downloader
        HttpClientDownloader httpClientDownloader = new HttpClientDownloader();

        //给下载器设置代理服务器信息
        httpClientDownloader.setProxyProvider(SimpleProxyProvider.from(new Proxy("123.163.97.227",9999)));

        Spider.create(new ProxyTest())
                .addUrl("http://ip.chinaz.com/getip.aspx")
                .setDownloader(httpClientDownloader)    //设置下载器
                .run();
    }

    @Override
    public void process(Page page) {
        //打印获取到的结果以测试代理服务器是否生效
        System.out.println(page.getHtml());
    }


    @Override
    public Site getSite() {
        return Site.me();

    }
}