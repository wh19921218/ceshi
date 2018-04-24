package com.cn.lianshou.entity;

import java.util.Date;

/**
 * FileName: com.cn.lianshou.entity.UrgeRepayLog.java
 * Author: Wanghh
 * Date: 2018/3/31 17:20
 */
public class UrgeRepayLog {

    /**
     * 主键
     */
    private Long id;
    /**
     * 催收订单id
     */
    private String urgeOrderId;
    /**
     * 借款订单id
     */
    private String orderNo;
    /**
     * 催款人id
     */
    private String urgeUserId;
    /**
     * 状态   20催收中;30承诺还款
     */
    private String state;
    /**
     * 备注说明
     */
    private String remark;
    /**
     * 承诺还款时间
     */
    private String promiseTime;
    /**
     * 催收时间
     */
    private Date createTime;
    /**
     * 催款方式   10 电话；20 邮件 ；30 短信；40现场沟通；50 其他
     */
    private String way;
    /**
     * 备用字段1
     */
    private String req_ext1;
    /**
     * 备用字段2
     */
    private String req_ext2;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrgeOrderId() {
        return urgeOrderId;
    }

    public void setUrgeOrderId(String urgeOrderId) {
        this.urgeOrderId = urgeOrderId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getUrgeUserId() {
        return urgeUserId;
    }

    public void setUrgeUserId(String urgeUserId) {
        this.urgeUserId = urgeUserId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPromiseTime() {
        return promiseTime;
    }

    public void setPromiseTime(String promiseTime) {
        this.promiseTime = promiseTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getWay() {
        return way;
    }

    public void setWay(String way) {
        this.way = way;
    }

    public String getReq_ext1() {
        return req_ext1;
    }

    public void setReq_ext1(String req_ext1) {
        this.req_ext1 = req_ext1;
    }

    public String getReq_ext2() {
        return req_ext2;
    }

    public void setReq_ext2(String req_ext2) {
        this.req_ext2 = req_ext2;
    }
}
