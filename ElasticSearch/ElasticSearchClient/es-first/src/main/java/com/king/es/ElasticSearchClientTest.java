package com.king.es;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContent;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.Before;
import org.junit.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @ProjectName: ElasticSearchClient
 * @Package: com.king.es
 * @ClassName: ElasticSearchClientTest
 * @Author: 王团结
 * @Description:
 * @Date: 2019/10/2 20:08
 * @Version: 1.0
 */
public class ElasticSearchClientTest {

    private TransportClient client;

    @Before
    public void init() throws Exception{
        //1.创建一个settings对象，相当于一个配置信息，主要配置集群的名称
        Settings settings = Settings.builder()
                .put("cluster.name", "my-elasticsearch")
                .build();

        //2.创建一个客户端Client对象
        client = new PreBuiltTransportClient(settings);
        client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"), 9301));
        client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"), 9302));
        client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"), 9303));
    }
    @Test
    public void createIndex() throws Exception {


        //3.使用client对象创建一个索引库
        client.admin().indices().prepareCreate("index_hello").get();

        //4.关闭client对象
        client.close();
    }

    @Test
    public void setMapping() throws Exception {
        //创建一个Mappings信息
        /*
        {
            "artile": {
                    "properties": {
                        "id": {
                            "type":"long",
                            "store":true
                        },
                        "title": {
                            "type":"text",
                            "store":true,
                            "index":true,
                            "analyzer":"ik_smart"
                        },
                        "content": {
                            "type":"text",
                            "store":true,
                            "index":true,
                            "analyzer":"ik_smart"
                        }
                }
            }
        }
        */

        XContentBuilder builder= XContentFactory.jsonBuilder()
                .startObject()
                    .startObject("article")
                        .startObject("properties")
                            .startObject("id")
                                .field("type","long")
                                .field("store",true)
                            .endObject()
                            .startObject("title")
                                .field("type","text")
                                .field("store",true)
                                .field("analyzer","ik_smart")
                            .endObject()
                            .startObject("content")
                                .field("type","text")
                                .field("store",true)
                                .field("analyzer","ik_smart")
                            .endObject()
                        .endObject()
                    .endObject()
                .endObject();

        client.admin().indices()
                .preparePutMapping("index_hello")
                .setType("article")
                .setSource(builder)
                .get();

        client.close();
    }

    @Test
    public void testAddDocument() throws Exception{
        XContentBuilder builder=XContentFactory.jsonBuilder()
                .startObject()
                    .field("id",2l)
                    .field("title","不负嘱托 决战东京——习近平会见中国女排代表回访2222")
                    .field("content","中国碗里装满中国粮 各部门全力迎接国庆黄金周出行高峰")
                .endObject();
        client.prepareIndex()
                .setIndex("index_hello")
                .setType("article")
                .setId("2")
                .setSource(builder)
                .get();
        client.close();

    }

    @Test
    public void testAddDocument2() throws Exception{
        Article article=new Article();
        article.setId(3L);
        article.setTitle("铁总：10月2日全国铁路预计发送旅客1360万人次");
        article.setContent("中企助力尼泊尔电信构建完善的全国4G通信网络");
        ObjectMapper objectMapper=new ObjectMapper();
        String jsonDocument = objectMapper.writeValueAsString(article);
        System.out.println(jsonDocument);

        client.prepareIndex("index_hello","article","3")
                .setSource(jsonDocument, XContentType.JSON)
                .get();
        client.close();
    }
    @Test
    public void testAddDocument3() throws Exception{
        for (int i = 4; i < 100; i++) {
            Article article=new Article();
            article.setId((long) i);
            article.setTitle("70周年成就展设1977年高考模拟考场，引三位部长共鸣"+i);
            article.setContent("较强冷空气影响北方 多地气温将迎断崖式下降"+i);
            ObjectMapper objectMapper=new ObjectMapper();
            String jsonDocument = objectMapper.writeValueAsString(article);
            System.out.println(jsonDocument);

            client.prepareIndex("index_hello","article",i+"")
                    .setSource(jsonDocument, XContentType.JSON)
                    .get();
        }

        client.close();
    }

}
