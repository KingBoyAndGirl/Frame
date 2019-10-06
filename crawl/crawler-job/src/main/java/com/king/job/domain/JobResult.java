package com.king.job.domain;

import java.util.List;

/**
 * @ProjectName: crawler-job
 * @Package: com.king.job.domain
 * @ClassName: JobResult
 * @Author: 王团结
 * @Description:
 * @Date: 2019/10/6 14:47
 * @Version: 1.0
 */
public class JobResult {

    private List<JobInfoField> rows;
    private Integer pageTotal;

    public List<JobInfoField> getRows() {
        return rows;
    }

    public void setRows(List<JobInfoField> rows) {
        this.rows = rows;
    }

    public Integer getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(Integer pageTotal) {
        this.pageTotal = pageTotal;
    }
}
