package com.king.crawler.test;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: crawlerfirst
 * @Package: com.king.crawler.test
 * @ClassName: HttpGetTest
 * @Author: 王团结
 * @Description:
 * @Date: 2019/10/4 9:30
 * @Version: 1.0
 */
public class HttpPostParamTest {
    public static void main(String[] args) throws Exception {
        //创建HttpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();

        //创建HttpGet对象，设置url访问地址
        HttpPost httpPost = new HttpPost("http://yun.itheima.com/search");

        //声明list集合，封装表单中的参数
        List<NameValuePair> params=new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("keys","java"));

        //创建表单的Entity对象，第一个参数就是封装好的表单数据，第二个参数就是编码
        UrlEncodedFormEntity formEntity=new UrlEncodedFormEntity(params,"utf-8");

        //设置表单的Entity对象到Post请求中
        httpPost.setEntity(formEntity);

        //使用HTTPClient发起请求，获取response
        CloseableHttpResponse response=null;
        try {
            response= httpClient.execute(httpPost);


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
