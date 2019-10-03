package com.king.es;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.Before;
import org.junit.Test;

import java.net.InetAddress;
import java.util.Iterator;
import java.util.Map;

/**
 * @ProjectName: ElasticSearchClient
 * @Package: com.king.es
 * @ClassName: SearchIndex
 * @Author: 王团结
 * @Description:
 * @Date: 2019/10/3 10:15
 * @Version: 1.0
 */
public class SearchIndex {
    private TransportClient client;

    @Before
    public void init() throws Exception {
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

    private void search(QueryBuilder queryBuilder) throws Exception {
        SearchResponse searchResponse = client.prepareSearch("index_hello")
                .setTypes("article")
                .setQuery(queryBuilder)
                //设置分页信息
                .setFrom(0)
                //每页显示的条数
                .setSize(5)
                .get();

        SearchHits searchHits = searchResponse.getHits();
        System.out.println("查询结果总记录数：" + searchHits.getTotalHits());
        Iterator<SearchHit> iterator = searchHits.iterator();
        while (iterator.hasNext()) {
            System.out.println("\n---------------------------------start");
            SearchHit searchHit = iterator.next();
            System.out.println(searchHit.getSourceAsString());
            System.out.println("---------------------------------文档的属性");
            Map<String, Object> document = searchHit.getSource();
            System.out.println(document.get("id"));
            System.out.println(document.get("title"));
            System.out.println(document.get("content"));
            System.out.println("---------------------------------end\n");
        }
        client.close();
    }

    private void search(QueryBuilder queryBuilder,String highlightField) throws Exception {
        HighlightBuilder highlightBuilder=new HighlightBuilder();
        //高亮显示的字段
        highlightBuilder.field(highlightField);
        highlightBuilder.preTags("<em>");
        highlightBuilder.postTags("</em>");
        SearchResponse searchResponse = client.prepareSearch("index_hello")
                .setTypes("article")
                .setQuery(queryBuilder)
                //设置分页信息
                .setFrom(0)
                //每页显示的条数
                .setSize(5)
                //设置高亮信息
                .highlighter(highlightBuilder)
                .get();

        SearchHits searchHits = searchResponse.getHits();
        System.out.println("查询结果总记录数：" + searchHits.getTotalHits());
        Iterator<SearchHit> iterator = searchHits.iterator();
        while (iterator.hasNext()) {
            System.out.println("\n---------------------------------start");
            SearchHit searchHit = iterator.next();
            System.out.println(searchHit.getSourceAsString());
            System.out.println("---------------------------------文档的属性");
            Map<String, Object> document = searchHit.getSource();
            System.out.println(document.get("id"));
            System.out.println(document.get("title"));
            System.out.println(document.get("content"));
            System.out.println("---------------------------------end\n");
            System.out.println("**************************************高亮结果");
            Map<String, HighlightField> highlightFields = searchHit.getHighlightFields();
            System.out.println(highlightFields);
            //去title高亮显示的结果
            HighlightField field = highlightFields.get(highlightField);
            Text[] fragments = field.getFragments();
            if (fragments!=null){
                String title = fragments[0].toString();
                System.out.println(title);
            }
        }
        client.close();
    }


    @Test
    public void testSearchById() throws Exception {
        QueryBuilder queryBuilder = QueryBuilders.idsQuery().addIds("1", "2");
        search(queryBuilder);
    }

    @Test
    public void testQueryByTerm() throws Exception {
        //参数1：要搜索的字段
        //参数2：要搜索的关键词
        QueryBuilder queryBuilder=QueryBuilders.termQuery("title","不负");
        search(queryBuilder);
    }

    @Test
    public void testQueryStringQuery() throws Exception{
        QueryBuilder queryBuilder=QueryBuilders.queryStringQuery("70周年")
                .defaultField("title");
        search(queryBuilder,"title");

    }
}
