package com.cn.lianshou.service;

import com.cn.lianshou.common.util.pagehelper.PageResult;
import com.cn.lianshou.entity.UrgeOrderInfo;

import java.util.List;
import java.util.Map;

/**
 * FileName: com.cn.lianshou.service.UrgeOrderInfoService.java
 * Author: Wanghh
 * Date: 2018/3/23 16:08
 */
public interface UrgeOrderInfoService {

    /**
     * 获取所有的催收订单
     * @return
     */
    List<UrgeOrderInfo> selectAll(Map<String, Object> map);

    /**
     * 催收订单  查询
     * @param page
     * @param rows
     * @param id 编号
     * @param urgeName 催收人姓名
     * @param borrowName 借款人姓名
     * @param channel 渠道名
     * @param orderNo 订单编号
     * @param phone 借款人手机号
     * @param timeLimit 借款期限
     * @param penaltyDay 逾期天数
     * @param state 订单状态
     * @param level 逾期等级
     * @return
     */
    PageResult selectUrgeOrderList(int page, int rows,Long id, String urgeName,String borrowName,String channel,String orderNo,String phone,String timeLimit,String penaltyDay,Integer state,String level);

    /**
     * 查询所有待还款订单
     * @return
     */
    List<UrgeOrderInfo> selectWaitRepayOrderList();

    /**
     * 插入订单
     * @param urgeOrderInfo
     * @return
     */
    int insert(UrgeOrderInfo urgeOrderInfo);

    /**
     * 根据状态查询  催收订单
     * @return
     */
    UrgeOrderInfo queryUrgeOrder(Map<String, Object> map);

    /**
     * 根据状态查询  催收订单并按金额排序
     * @param state
     * @return
     */
    List<UrgeOrderInfo> selectByState(Integer state);
    /**
     * 根据id修改 催收人员姓名
     * @param urgeName
     * @param id
     * @return
     */
    int updateUrgeNameById(Long urgeUserId, String urgeName,List<Long> id);

    /**
     * 根据id修改 订单状态
     * @param state
     * @param id
     * @return
     */
    int updateUrgeStateById(Integer state,Long id);

    /**
     * 修改订单信息
     * @param map
     * @return
     */
    int updateSelective(Map<String, Object> map);
}
