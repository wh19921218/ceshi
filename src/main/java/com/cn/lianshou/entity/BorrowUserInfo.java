package com.cn.lianshou.entity;

import java.util.Date;

/**
 * FileName: com.cn.lianshou.entity.BorrowUserInfo.java
 * Author: Wanghh
 * Date: 2018/3/24 14:30
 */
public class BorrowUserInfo {

    /**
     * 主键
     */
    private Long id;
    /**
     * 渠道
     */
    private String channel;
    /**
     * 真实姓名
     */
    private String realName;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 性别
     */
    private String sex;
    /**
     * 年龄
     */
    private String age;
    /**
     * 身份证号
     */
    private String idNo;
    /**
     * 紧急联系人1姓名
     */
    private String urgeNameOne;
    /**
     * 紧急联系人1关系
     */
    private String urgeRelationOne;
    /**
     * 紧急联系人1电话
     */
    private String urgePhoneOne;
    /**
     * 紧急联系人2姓名
     */
    private String urgeNameTow;
    /**
     * 紧急联系人2关系
     */
    private String urgeRelationTow;
    /**
     * 紧急联系人2电话
     */
    private String urgePhoneTow;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date updateTime;
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

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getUrgeNameOne() {
        return urgeNameOne;
    }

    public void setUrgeNameOne(String urgeNameOne) {
        this.urgeNameOne = urgeNameOne;
    }

    public String getUrgeRelationOne() {
        return urgeRelationOne;
    }

    public void setUrgeRelationOne(String urgeRelationOne) {
        this.urgeRelationOne = urgeRelationOne;
    }

    public String getUrgePhoneOne() {
        return urgePhoneOne;
    }

    public void setUrgePhoneOne(String urgePhoneOne) {
        this.urgePhoneOne = urgePhoneOne;
    }

    public String getUrgeNameTow() {
        return urgeNameTow;
    }

    public void setUrgeNameTow(String urgeNameTow) {
        this.urgeNameTow = urgeNameTow;
    }

    public String getUrgeRelationTow() {
        return urgeRelationTow;
    }

    public void setUrgeRelationTow(String urgeRelationTow) {
        this.urgeRelationTow = urgeRelationTow;
    }

    public String getUrgePhoneTow() {
        return urgePhoneTow;
    }

    public void setUrgePhoneTow(String urgePhoneTow) {
        this.urgePhoneTow = urgePhoneTow;
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
