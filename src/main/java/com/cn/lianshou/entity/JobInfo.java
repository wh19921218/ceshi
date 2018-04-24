package com.cn.lianshou.entity;

import java.util.Date;

/**
 * Author: Wanghh
 * Date: 2018/4/13 14:14
 */
public class JobInfo {

    /**
     * ID
     */
    private Long id;
    /**
     * 任务名称
     */
    private String name;
    /**
     * 任务标识
     */
    private String code;

    /**
     * Job服务类
     */
    private String jobService;
    /**
     * 定时任务执行周期
     */
    private String cycle;
    /**
     * 是否启用 0-启用 1-禁用
     */
    private String state;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date updateTime;
    /**
     * 修改人id
     */
    private Long updateUserId;
    /**
     * 扩展字段1
     */
    private String reqExt1;
    /**
     * 扩展字段2
     */
    private String reqExt2;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getJobService() {
        return jobService;
    }

    public void setJobService(String jobService) {
        this.jobService = jobService;
    }

    public String getCycle() {
        return cycle;
    }

    public void setCycle(String cycle) {
        this.cycle = cycle;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Long updateUserId) {
        this.updateUserId = updateUserId;
    }

    public String getReqExt1() {
        return reqExt1;
    }

    public void setReqExt1(String reqExt1) {
        this.reqExt1 = reqExt1;
    }

    public String getReqExt2() {
        return reqExt2;
    }

    public void setReqExt2(String reqExt2) {
        this.reqExt2 = reqExt2;
    }
}
