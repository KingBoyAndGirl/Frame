package com.king.test;

import com.king.CrawlerJobApplication;
import com.king.job.domain.JobInfo;
import com.king.job.domain.JobInfoField;
import com.king.job.service.JobInfoService;
import com.king.job.service.JobRepositoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: crawlerfirst
 * @Package: com.king.test
 * @ClassName: ElasticSearchTest
 * @Author: 王团结
 * @Description:
 * @Date: 2019/10/6 11:29
 * @Version: 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = CrawlerJobApplication.class)
public class ElasticSearchTest {

    @Autowired
    private JobInfoService jobInfoService;
    @Autowired
    private JobRepositoryService jobRepositoryService;
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    /**
     * 创建索引和映射
     */
    @Test
    public void createIndex() {
        this.elasticsearchTemplate.createIndex(JobInfoField.class);
        this.elasticsearchTemplate.putMapping(JobInfoField.class);
    }


    @Test
    public void jobData() {
        //声明页码数，从1楷书
        int currentPage=1;
        //声明查询到的数据条数
        int pageSize=0;
        do {


            //从数据库中查询数据
            Page<JobInfo> page = jobInfoService.findJobInfoByPage(currentPage, 500);

            //声明容器存放JobInfoField
            List<JobInfoField> list = new ArrayList<>();

            //把查询到的数据封装为JobInfoFiled
            for (JobInfo jobInfo : page.getContent()) {
                //声明对象
                JobInfoField jobInfoField = new JobInfoField();
                //封装数据，复制数据
                BeanUtils.copyProperties(jobInfo, jobInfoField);

                //把封装好数据的对象放到list容器中
                list.add(jobInfoField);
            }

            //把封装好的数据保存到索引库
            jobRepositoryService.saveAll(list);

            //页码数加1
            currentPage++;

            //获取查询结果集的数据条数
            pageSize=page.getContent().size();

        } while (pageSize==500);
    }
}