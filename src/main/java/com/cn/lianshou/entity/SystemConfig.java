package com.cn.lianshou.entity;

import java.util.Date;

/**
 * FileName: com.cn.lianshou.entity.SystemConfigService.java
 * Author: Wanghh
 * Date: 2018/4/4 16:10
 */
public class SystemConfig {

    /**
     * id
     */
    private Long id;
    /**
     * 渠道名称
     */
    private String channel;
    /**
     * 借款期限
     */
    private String timeLimit;
    /**
     * 期限单位
     */
    private String unit;
    /**
     * 逾期利率
     */
    private String penaltyFee;
    /**
     * 逾期罚金上限  借款金额的百分之多少
     */
    private String penaltyAmountMax;
    /**
     * 是否启用 10-启用 20-禁用
     */
    private String state;
    /**
     * 修改人id
     */
    private Long updateUserId;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date updateTime;
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

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(String timeLimit) {
        this.timeLimit = timeLimit;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getPenaltyFee() {
        return penaltyFee;
    }

    public void setPenaltyFee(String penaltyFee) {
        this.penaltyFee = penaltyFee;
    }

    public String getPenaltyAmountMax() {
        return penaltyAmountMax;
    }

    public void setPenaltyAmountMax(String penaltyAmountMax) {
        this.penaltyAmountMax = penaltyAmountMax;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Long getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Long updateUserId) {
        this.updateUserId = updateUserId;
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
