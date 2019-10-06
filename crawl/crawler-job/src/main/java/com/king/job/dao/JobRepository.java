package com.king.job.dao;

import com.king.job.domain.JobInfoField;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

/**
 * @ProjectName: crawler-job
 * @Package: com.king.job.dao
 * @ClassName: JobRepository
 * @Author: 王团结
 * @Description:
 * @Date: 2019/10/6 13:42
 * @Version: 1.0
 */
@Component
public interface JobRepository extends ElasticsearchRepository<JobInfoField,Long> {
    /**
     * 根据条件分页查询数据
     *
     * @param salaryMin1 薪资下限最小值
     * @param salaryMin2 薪资下限最高值
     * @param salaryMax1 薪资上限最小值
     * @param salaryMax2 薪资上限最大值
     * @param jobAddr    工作地点
     * @param jobName    职位名称
     * @param jobInfo    职位信息
     * @param pageable   分页数据
     * @return
     */
    public Page<JobInfoField> findBySalaryMinBetweenAndSalaryMaxBetweenAndJobAddrAndJobNameAndJobInfo(int salaryMin1, int salaryMin2, int salaryMax1, int salaryMax2,
                                                                                                      String jobAddr, String jobName, String jobInfo, Pageable pageable);
}
