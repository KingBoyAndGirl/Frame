package com.king.job.service;

import com.king.job.domain.JobInfo;

import java.util.List;

/**
 * @ProjectName: crawlerfirst
 * @Package: com.king.job.service
 * @ClassName: JobInfoService
 * @Author: 王团结
 * @Description:
 * @Date: 2019/10/5 16:25
 * @Version: 1.0
 */
public interface JobInfoService {

    /**
     * 保存数据
     *
     * @param jobInfo
     */
    public void save(JobInfo jobInfo);

    /**
     * 根据条件查询数据
     *
     * @param jobInfo
     * @return
     */
    public List<JobInfo> findJobInfo(JobInfo jobInfo);
}
