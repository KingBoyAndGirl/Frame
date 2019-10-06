package com.king.job.dao;

import com.king.job.domain.JobInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @ProjectName: crawlerfirst
 * @Package: com.king.job.dao
 * @ClassName: JobInfoDao
 * @Author: 王团结
 * @Description:
 * @Date: 2019/10/5 16:23
 * @Version: 1.0
 */
public interface JobInfoDao extends JpaRepository<JobInfo,Long> {
}
