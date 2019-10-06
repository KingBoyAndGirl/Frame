package com.king.job.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @ProjectName: crawler-job
 * @Package: com.king.job.domain
 * @ClassName: JobInfoField
 * @Author: 王团结
 * @Description:
 * @Date: 2019/10/6 13:40
 * @Version: 1.0
 */
@Document(indexName = "jobinfo", type = "jobInfoField")
public class JobInfoField {

    @Id
    @Field(index = true, store = true, type = FieldType.Long)
    private Long id;
    @Field(index = false, store = true, type = FieldType.Text)
    private String companyName;
    @Field(index = false, store = true, type = FieldType.Text)
    private String companyAddr;
    @Field(index = false, store = true, type = FieldType.Text)
    private String companyInfo;
    @Field(index = true, store = true, analyzer = "ik_smart", searchAnalyzer = "ik_smart", type = FieldType.Text)
    private String companyUrl;
    private String jobName;
    @Field(index = true, store = true, analyzer = "ik_smart", searchAnalyzer = "ik_smart", type = FieldType.Text)
    private String jobAddr;
    @Field(index = true, store = false, analyzer = "ik_smart", searchAnalyzer = "ik_smart", type = FieldType.Text)
    private String jobInfo;
    private String jobUrl;
    @Field(index = true, store = true, type = FieldType.Integer)
    private Integer salaryMin;
    @Field(index = true, store = true, type = FieldType.Integer)
    private Integer salaryMax;
    @Field(index = true, store = true, type = FieldType.Text)
    private String time;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyAddr() {
        return companyAddr;
    }

    public void setCompanyAddr(String companyAddr) {
        this.companyAddr = companyAddr;
    }

    public String getCompanyInfo() {
        return companyInfo;
    }

    public void setCompanyInfo(String companyInfo) {
        this.companyInfo = companyInfo;
    }

    public String getCompanyUrl() {
        return companyUrl;
    }

    public void setCompanyUrl(String companyUrl) {
        this.companyUrl = companyUrl;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobAddr() {
        return jobAddr;
    }

    public void setJobAddr(String jobAddr) {
        this.jobAddr = jobAddr;
    }

    public String getJobInfo() {
        return jobInfo;
    }

    public void setJobInfo(String jobInfo) {
        this.jobInfo = jobInfo;
    }

    public String getJobUrl() {
        return jobUrl;
    }

    public void setJobUrl(String jobUrl) {
        this.jobUrl = jobUrl;
    }

    public Integer getSalaryMin() {
        return salaryMin;
    }

    public void setSalaryMin(Integer salaryMin) {
        this.salaryMin = salaryMin;
    }

    public Integer getSalaryMax() {
        return salaryMax;
    }

    public void setSalaryMax(Integer salaryMax) {
        this.salaryMax = salaryMax;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "JobInfoField{" +
                "id=" + id +
                ", companyName='" + companyName + '\'' +
                ", companyAddr='" + companyAddr + '\'' +
                ", companyInfo='" + companyInfo + '\'' +
                ", companyUrl='" + companyUrl + '\'' +
                ", jobName='" + jobName + '\'' +
                ", jobAddr='" + jobAddr + '\'' +
                ", jobInfo='" + jobInfo + '\'' +
                ", jobUrl='" + jobUrl + '\'' +
                ", salaryMin=" + salaryMin +
                ", salaryMax=" + salaryMax +
                ", time='" + time + '\'' +
                '}';
    }
}
