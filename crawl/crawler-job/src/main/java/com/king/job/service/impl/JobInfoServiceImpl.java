package com.king.job.service.impl;

import com.king.job.dao.JobInfoDao;
import com.king.job.domain.JobInfo;
import com.king.job.service.JobInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ProjectName: crawlerfirst
 * @Package: com.king.job.service.impl
 * @ClassName: JobInfoServiceImpl
 * @Author: 王团结
 * @Description:
 * @Date: 2019/10/5 16:26
 * @Version: 1.0
 */
@Service
public class JobInfoServiceImpl implements JobInfoService {

    @Autowired
    private JobInfoDao jobInfoDao;

    @Override
    @Transactional
    public void save(JobInfo jobInfo) {
        //根据url和发布时间查询数据
        JobInfo param=new JobInfo();
        param.setUrl(jobInfo.getUrl());
        param.setTime(jobInfo.getTime());

        //执行查询
        List<JobInfo> list = findJobInfo(param);

        //判断查询结果是否为空
        if (list.size()==0) {
            //没有查询到数据则新增或者修改数据
            jobInfoDao.saveAndFlush(jobInfo);
        }
        
    }

    @Override
    public List<JobInfo> findJobInfo(JobInfo jobInfo) {
        //设置查询条件
        Example example = Example.of(jobInfo);

        //执行查询
        return jobInfoDao.findAll(example);
    }
}
