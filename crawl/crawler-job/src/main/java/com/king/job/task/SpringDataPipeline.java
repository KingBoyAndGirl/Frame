package com.king.job.task;

import com.king.job.domain.JobInfo;
import com.king.job.service.JobInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

/**
 * @ProjectName: crawlerfirst
 * @Package: com.king.job.task
 * @ClassName: SpringDataPipeline
 * @Author: 王团结
 * @Description:
 * @Date: 2019/10/5 20:19
 * @Version: 1.0
 */
@Component
public class SpringDataPipeline implements Pipeline {

    @Autowired
    private JobInfoService jobInfoService;

    @Override
    public void process(ResultItems resultItems, Task task) {
        //获取封装好的招聘详情对象
        JobInfo jobInfo=resultItems.get("jobInfo");

        //判断数据是否不为空
        if (jobInfo!=null) {
            //如果不为空把数据保存到数据库中
            jobInfoService.save(jobInfo);
        }
    }
}
