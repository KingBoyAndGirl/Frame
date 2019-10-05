package com.king.task;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.king.domain.Item;
import com.king.service.ItemService;
import com.king.utils.HttpUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;
import java.util.List;


/**
 * @ProjectName: crawlerfirst
 * @Package: com.king.task
 * @ClassName: ItemTask
 * @Author: 王团结
 * @Description:
 * @Date: 2019/10/4 18:28
 * @Version: 1.0
 */
@Component
public class ItemTask {

    @Autowired
    private HttpUtils httpUtils;

    @Autowired
    private ItemService itemService;

    private static final ObjectMapper MAPPER=new ObjectMapper();

    /**
     * fixedDelay当下载任务完成后，间隔多长时间进行下次的任务
     * @throws Exception
     */
    @Scheduled(fixedDelay = 100*1000)
    public void itemTask() throws Exception{
        //声明需要解析的初始化地址
        String url="https://search.jd.com/Search?keyword=%E6%89%8B%E6%9C%BA&enc=utf-8&qrst=1&rt=1&stop=1&vt=2&wq=%E6%89%8B%E6%9C%BA&cid2=653&cid3=655&s=1&click=0&page=";
        //按照页码对手机的搜索结果进行遍历解析
        for (int i = 1; i < 10; i+=2) {
            String html = httpUtils.doGetHtml(url + i);

            //解析页面，获取商品数据并存储

            this.parse(html);
        }

        System.out.println("手机数据抓取完成！");
    }

    /**
     * 解析页面，获取商品数据并存储
     * @param html
     */
    private void parse(String html) throws Exception {
        //解析html获取Document
        Document document = Jsoup.parse(html);

        //获取li(spu)集合信息
        Elements spuElements = document.select("div#J_goodsList > ul > li");

        for (Element spuElement : spuElements) {
            //获取spu
            long spu = Long.parseLong(spuElement.attr("data-spu"));

            //获取li(sku)集合信息
            Elements skuElements = spuElement.select("li.ps-item");

            for (Element skuElement : skuElements) {
                //获取sku
                long sku = Long.parseLong(skuElement.select("[data-sku]").attr("data-sku"));

                //根据sku查询商品数据
                Item item = new Item();
                item.setSku(sku);
                List<Item> list = itemService.findAll(item);


                if (list.size()>0) {
                    //如果商品存在，就进行下一个循环，该商品不保存（因为已经存在）
                    continue;
                }

                //设置商品的spu
                item.setSpu(spu);

                //获取商品的详情的url
                String itemUrl="https://item.jd.com/"+sku+".html";

                item.setUrl(itemUrl);

                //获取商品的图片
                String picUrl = "https:"+skuElement.select("img[data-sku]").first().attr("data-lazy-img");
                if (picUrl.length()<7){
                    System.out.println("商品的spu"+spu);
                   continue;
                }
                picUrl=picUrl.replace("/n9/","/n1/");
                String picName = httpUtils.doGetImage(picUrl);

                item.setPic(picName);


                //获取商品的价格
                String priceUrl = "https://p.3.cn/prices/mgets?skuIds=J_"+sku;
                String priceJson = httpUtils.doGetHtml(priceUrl);
                double price = MAPPER.readTree(priceJson).get(0).get("p").asDouble();
                item.setPrice(price);

                //获取商品的标题
                String itemInfo = httpUtils.doGetHtml(item.getUrl());
                String title = Jsoup.parse(itemInfo).select("div.sku-name").text();
                item.setTitle(title);

                //获取商品的创建时间
                item.setCreated(new Date());

                //获取商品的更新时间
                item.setUpdated(item.getCreated());
                itemService.save(item);
            }
        }

    }
}
