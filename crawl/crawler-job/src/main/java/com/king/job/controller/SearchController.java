package com.king.job.controller;

import com.king.job.domain.JobResult;
import com.king.job.service.JobRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ProjectName: crawler-job
 * @Package: com.king.job.controller
 * @ClassName: SearchController
 * @Author: 王团结
 * @Description:
 * @Date: 2019/10/6 14:49
 * @Version: 1.0
 */
@RestController
public class SearchController {
    @Autowired
    private JobRepositoryService jobRepositoryService;

    //salary: *-*
    //page: 1
    //jobaddr: 北京
    //keyword: java
    //jobarea: 010000
    //Request URL: http://localhost:8080/search
    //Request Method: POST
    @RequestMapping(value = "search",method = RequestMethod.POST)
    public JobResult search(String salary,String jobaddr,String keyword,Integer page){
            return jobRepositoryService.search(salary,jobaddr,keyword,page);
    }
}
