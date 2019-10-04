package com.king.crawler.test;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @ProjectName: crawlerfirst
 * @Package: com.king.crawler.test
 * @ClassName: HttpGetTest
 * @Author: 王团结
 * @Description:
 * @Date: 2019/10/4 9:30
 * @Version: 1.0
 */
public class HttpGetTest {
    public static void main(String[] args) {
        //创建HttpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();

        //创建HttpGet对象，设置url访问地址
        HttpGet httpGet = new HttpGet("http://www.itcast.cn");


        //使用HTTPClient发起请求，获取response
        CloseableHttpResponse response=null;
        try {
            response= httpClient.execute(httpGet);


            //解析响应
            if (response.getStatusLine().getStatusCode()==200){
                String content = EntityUtils.toString(response.getEntity(), "utf-8");
                System.out.println(content.length());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            //关闭response
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
