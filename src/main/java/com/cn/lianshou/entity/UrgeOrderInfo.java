package com.cn.lianshou.entity;

/**
 * FileName: com.cn.lianshou.entity.UrgeOrderInfo.java
 * Author: Wanghh
 * Date: 2018/3/23 15:56
 */
public class UrgeOrderInfo {

    /**
     * id
     */
    private Long id;
    /**
     * 催收人id
     */
    private Long urgeUserId;
    /**
     * 催收人姓名
     */
    private String urgeName;
    /**
     * 借款人姓名
     */
    private String borrowName;
    /**
     * 渠道
     */
    private String channel;
    /**
     * 订单编号
     */
    private String orderNo;
    /**
     * 借款人手机号
     */
    private String phone;
    /**
     * 借款金额
     */
    private String amount;
    /**
     * 借款金额
     */
    private String realAmount;
    /**
     * 借款时间
     */
    private String borrowTime;
    /**
     * 借款期限
     */
    private String timeLimit;
    /**
     * 期限单位：1天，2月
     */
    private Integer unit;
    /**
     * 预计还款时间
     */
    private String repayTime;
    /**
     * 逾期天数
     */
    private String penaltyDay;
    /**
     * 逾期金额
     */
    private String penaltyAmout;
    /**
     * 订单状态   10未分配;11待催收;20催收中;30承诺还款;40催收成功;50坏账
     */
    private Integer state;
    /**
     * 逾期等级  M1 (1-30天)  M2 (31-60天)  M3 (61以上)
     */
    private String level;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 修改时间
     */
    private String updateTime;
    /**
     * 备用字段1
     */
    private String reqExt1;
    /**
     * 备用字段2
     */
    private String reqExt2;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUrgeUserId() {
        return urgeUserId;
    }

    public void setUrgeUserId(Long urgeUserId) {
        this.urgeUserId = urgeUserId;
    }

    public String getUrgeName() {
        return urgeName;
    }

    public void setUrgeName(String urgeName) {
        this.urgeName = urgeName;
    }

    public String getBorrowName() {
        return borrowName;
    }

    public void setBorrowName(String borrowName) {
        this.borrowName = borrowName;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getRealAmount() {
        return realAmount;
    }

    public void setRealAmount(String realAmount) {
        this.realAmount = realAmount;
    }

    public String getBorrowTime() {
        return borrowTime;
    }

    public void setBorrowTime(String borrowTime) {
        this.borrowTime = borrowTime;
    }

    public String getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(String timeLimit) {
        this.timeLimit = timeLimit;
    }

    public Integer getUnit() {
        return unit;
    }

    public void setUnit(Integer unit) {
        this.unit = unit;
    }

    public String getRepayTime() {
        return repayTime;
    }

    public void setRepayTime(String repayTime) {
        this.repayTime = repayTime;
    }

    public String getPenaltyDay() {
        return penaltyDay;
    }

    public void setPenaltyDay(String penaltyDay) {
        this.penaltyDay = penaltyDay;
    }

    public String getPenaltyAmout() {
        return penaltyAmout;
    }

    public void setPenaltyAmout(String penaltyAmout) {
        this.penaltyAmout = penaltyAmout;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
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
