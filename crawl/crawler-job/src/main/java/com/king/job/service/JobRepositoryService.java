package com.king.job.service;

import com.king.job.domain.JobInfoField;
import com.king.job.domain.JobResult;

import java.util.List;

/**
 * @ProjectName: crawler-job
 * @Package: com.king.job.service
 * @ClassName: JobRepositoryService
 * @Author: 王团结
 * @Description:
 * @Date: 2019/10/6 13:44
 * @Version: 1.0
 */
public interface JobRepositoryService {

    /**
     * 保存一条数据
     *
     * @param jobInfoField
     */
    void save(JobInfoField jobInfoField);

    /**
     * 批量保存数据
     *
     * @param list
     */
    void saveAll(List<JobInfoField> list);

    /**
     * 根据条件分页查询招聘信息
     * @param salary
     * @param jobaddr
     * @param keyword
     * @param page
     * @return
     */
    JobResult search(String salary, String jobaddr, String keyword, Integer page);
}
