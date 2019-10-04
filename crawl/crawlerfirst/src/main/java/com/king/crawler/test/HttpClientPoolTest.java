package com.king.crawler.test;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @ProjectName: crawlerfirst
 * @Package: com.king.crawler.test
 * @ClassName: HttpClientPool
 * @Author: 王团结
 * @Description:
 * @Date: 2019/10/4 10:41
 * @Version: 1.0
 */
public class HttpClientPoolTest {

    public static void main(String[] args) {
        //创建连接池管理器
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();

        //设置最大连接数连接数
        cm.setMaxTotal(100);

        //设置每个主机的最大连接数
        cm.setDefaultMaxPerRoute(10);

        //使用连接池管理器发起请求
        doGet(cm);
        doGet(cm);
    }

    private static void doGet(PoolingHttpClientConnectionManager cm) {
        //不是每次创建新的HTTPClient，而是从连接池中获取HTTPClient对象
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(cm).build();
        HttpGet httpGet = new HttpGet("http://www.itcast.cn");
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);

            if (response.getStatusLine().getStatusCode() == 200) {
                String content = EntityUtils.toString(response.getEntity(), "utf-8");
                System.out.println(content.length());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                //不能关闭HTTPClient，由连接池管理HTTPClient
            }
        }
    }
}
