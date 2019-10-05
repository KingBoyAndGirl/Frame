package com.king.utils;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.UUID;

/**
 * @ProjectName: crawlerfirst
 * @Package: com.king.utils
 * @ClassName: HttpUtils
 * @Author: 王团结
 * @Description:
 * @Date: 2019/10/4 13:50
 * @Version: 1.0
 */
@Component
public class HttpUtils {

    private PoolingHttpClientConnectionManager cm;

    public HttpUtils() {
        this.cm = new PoolingHttpClientConnectionManager();

        //设置最大连接数
        cm.setMaxTotal(200);

        //设置每个主机的并发数
        cm.setDefaultMaxPerRoute(20);
    }

    /**
     * 根据请求地址下载页面数据
     * @param url
     * @return
     */
    public String doGetHtml(String url) {
        // 获取HttpClient对象
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(cm).build();

        // 声明httpGet请求对象
        HttpGet httpGet = new HttpGet(url);
        /**
         * 针对登录验证解决方案
         */
        httpGet.setHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/76.0.3809.132 Safari/537.36");
        httpGet.setHeader("Cookie","shshshfpa=371040be-7b1b-70a9-5a5a-58febc486c0f-1569037680; shshshfpb=iEpgRnmrQsjoydtCvkgsaTQ%3D%3D; __jdu=1826693278; areaId=7; ipLoc-djd=7-527-35108-0; PCSYCityID=CN_410000_411600_411602; xtest=8505.cf6b6759; qrsc=3; pinId=2Ex2bgFGAMhridEgcxLVh7V9-x-f3wj7; pin=331003977-172926; unick=qq1782635977; _tp=f0tnd8L1cil2nEnEN5%2BTsrUJXU0c%2FteQO5bHFYvI%2BgY%3D; _pst=331003977-172926; unpl=V2_ZzNtbUoDQBN2C05XexALUGIEEw0RUktCIg9CBn9OVQMwVBFbclRCFX0URlVnGFkUZAMZX0BcRhFFCEdkeBBVAWMDE1VGZxBFLV0CFSNGF1wjU00zQwBBQHcJFF0uSgwDYgcaDhFTQEJ2XBVQL0oMDDdRFAhyZ0AVRQhHZHsQXwJgCxBeRF5zJXI4dmR%2fHlQBbgYiXHJWc1chVEZSfB9fAioDG15FUEsXdg5PZHopXw%3d%3d; TrackID=1WJRNLxjqlKUznSlYizIoCBxXww0PVAO6jA-zoNuRb17Ck9GEdP8bE_7sadYvs1ztDpk-DrxA97RuTyiZf0bV-1hgnr0smBMexe8kY-rJlQY; thor=83F667384D19034BDBB2B09CD66AFCB33363E9CC481CB10E1A3ACF2F7F4ADEA805D76233A6D351C984343C1BD05377BB08CDDAAE51EDEEA694D8C6D03BE9A5A7CA651A966E7FD1530192CC5794647216DACFEA3240472C224788D1A58FB0C0654527CA54B004E3DDEFCBB688DB89C1089A85AF58F49F54F5241E9B665FA1CB9AC7E85C8E97D1638AA51D876808599481ABD15D12B52C269EBD1B2FCEAF3E68CF; ceshi3.com=103; __jda=76161171.1826693278.1569037678.1570193352.1570193566.7; __jdc=76161171; user-key=2a24d6a9-7e4e-4aaf-9736-1e3e632646f2; cn=1; __jdb=76161171.43.1826693278|7.1570193566; __jdv=76161171|baidu-pinzhuan|t_288551095_baidupinzhuan|cpc|0f3d30c8dba7459bb52f2eb5eba8ac7d_0_9e27229218fd460ab49ff65c5f87ff27|1570196147539; shshshfp=9d8a012186307617109e005cf25e6f63; shshshsID=af1f439524fd70be9ab5086a8b05ddf1_11_1570196148663");
        httpGet.setHeader("Connection","keep-alive");

        // 设置请求参数RequestConfig
        httpGet.setConfig(this.getConfig());

        CloseableHttpResponse response = null;
        try {
            // 使用HttpClient发起请求，返回response
            response = httpClient.execute(httpGet);
            // 解析response返回数据
            if (response.getStatusLine().getStatusCode() == 200) {
                String html = "";

                // 如果response。getEntity获取的结果是空，在执行EntityUtils.toString会报错
                // 需要对Entity进行非空的判断
                if (response.getEntity() != null) {
                    html = EntityUtils.toString(response.getEntity(), "UTF-8");
                }

                return html;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null) {
                    // 关闭连接
                    response.close();
                }
                // 不能关闭，现在使用的是连接管理器
                // httpClient.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 下载图片
     * @param url
     * @return
     */
    public String doGetImage(String url) {
        // 获取HttpClient对象
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(cm).build();

        // 声明httpGet请求对象
        HttpGet httpGet = new HttpGet(url);
        /**
         * 针对登录验证解决方案
         */
        httpGet.setHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/76.0.3809.132 Safari/537.36");
        httpGet.setHeader("Cookie","shshshfpa=371040be-7b1b-70a9-5a5a-58febc486c0f-1569037680; shshshfpb=iEpgRnmrQsjoydtCvkgsaTQ%3D%3D; __jdu=1826693278; areaId=7; ipLoc-djd=7-527-35108-0; PCSYCityID=CN_410000_411600_411602; xtest=8505.cf6b6759; qrsc=3; pinId=2Ex2bgFGAMhridEgcxLVh7V9-x-f3wj7; pin=331003977-172926; unick=qq1782635977; _tp=f0tnd8L1cil2nEnEN5%2BTsrUJXU0c%2FteQO5bHFYvI%2BgY%3D; _pst=331003977-172926; unpl=V2_ZzNtbUoDQBN2C05XexALUGIEEw0RUktCIg9CBn9OVQMwVBFbclRCFX0URlVnGFkUZAMZX0BcRhFFCEdkeBBVAWMDE1VGZxBFLV0CFSNGF1wjU00zQwBBQHcJFF0uSgwDYgcaDhFTQEJ2XBVQL0oMDDdRFAhyZ0AVRQhHZHsQXwJgCxBeRF5zJXI4dmR%2fHlQBbgYiXHJWc1chVEZSfB9fAioDG15FUEsXdg5PZHopXw%3d%3d; TrackID=1WJRNLxjqlKUznSlYizIoCBxXww0PVAO6jA-zoNuRb17Ck9GEdP8bE_7sadYvs1ztDpk-DrxA97RuTyiZf0bV-1hgnr0smBMexe8kY-rJlQY; thor=83F667384D19034BDBB2B09CD66AFCB33363E9CC481CB10E1A3ACF2F7F4ADEA805D76233A6D351C984343C1BD05377BB08CDDAAE51EDEEA694D8C6D03BE9A5A7CA651A966E7FD1530192CC5794647216DACFEA3240472C224788D1A58FB0C0654527CA54B004E3DDEFCBB688DB89C1089A85AF58F49F54F5241E9B665FA1CB9AC7E85C8E97D1638AA51D876808599481ABD15D12B52C269EBD1B2FCEAF3E68CF; ceshi3.com=103; __jda=76161171.1826693278.1569037678.1570193352.1570193566.7; __jdc=76161171; user-key=2a24d6a9-7e4e-4aaf-9736-1e3e632646f2; cn=1; __jdb=76161171.43.1826693278|7.1570193566; __jdv=76161171|baidu-pinzhuan|t_288551095_baidupinzhuan|cpc|0f3d30c8dba7459bb52f2eb5eba8ac7d_0_9e27229218fd460ab49ff65c5f87ff27|1570196147539; shshshfp=9d8a012186307617109e005cf25e6f63; shshshsID=af1f439524fd70be9ab5086a8b05ddf1_11_1570196148663");
        httpGet.setHeader("Connection","keep-alive");

        // 设置请求参数RequestConfig
        httpGet.setConfig(this.getConfig());
        
        CloseableHttpResponse response = null;
        try {
            // 使用HttpClient发起请求，返回response
            response = httpClient.execute(httpGet);
            // 解析response下载图片
            if (response.getStatusLine().getStatusCode() == 200) {

                // 如果response。getEntity获取的结果是空，在执行EntityUtils.toString会报错
                // 需要对Entity进行非空的判断
                if (response.getEntity() != null) {

                    // 获取文件类型
                    String extName = url.substring(url.lastIndexOf("."));
                    // 使用uuid生成图片名称（重命名）
                    String imageName = UUID.randomUUID().toString() + extName;

                    // 声明输出的文件
                    OutputStream outStream = new FileOutputStream(new File("D:\\Project\\frame\\crawl\\crawlerjd\\src\\main\\resources\\images\\" + imageName));
                    // 使用响应体输出文件
                    response.getEntity().writeTo(outStream);

                    // 返回生成的图片名
                    return imageName;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null) {
                    // 关闭连接
                    response.close();
                }
                // 不能关闭，现在使用的是连接管理器
                // httpClient.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    //获取请求参数对象
    private RequestConfig getConfig() {
        return RequestConfig.custom().setConnectTimeout(1000)// 设置创建连接的超时时间
                .setConnectionRequestTimeout(500) // 设置获取连接的超时时间
                .setSocketTimeout(10000) // 设置连接的超时时间
                .build();
    }
}
