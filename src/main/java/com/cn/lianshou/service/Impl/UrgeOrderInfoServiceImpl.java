package com.cn.lianshou.service.Impl;

import com.cn.lianshou.common.util.pagehelper.PageInfo;
import com.cn.lianshou.common.util.pagehelper.PageResult;
import com.cn.lianshou.mapper.UrgeOrderInfoMapper;
import com.cn.lianshou.entity.UrgeOrderInfo;
import com.cn.lianshou.service.UrgeOrderInfoService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * FileName: com.cn.lianshou.service.Impl.UrgeOrderInfoServiceImpl.java
 * Author: Wanghh
 * Date: 2018/3/23 16:09
 */
@Service("urgeOrderInfoService")
public class UrgeOrderInfoServiceImpl implements UrgeOrderInfoService {

    @Autowired
    private UrgeOrderInfoMapper urgeOrderInfoMapper;


    /**
     * 获取所有的 催收订单
     * @return
     */
    public List<UrgeOrderInfo> selectAll(Map<String, Object> map) {

        return urgeOrderInfoMapper.selectAll(map);
    }

    /**
     * 获取所有的 待分配订单
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
    public PageResult selectUrgeOrderList(int page, int rows,Long id, String urgeName,String borrowName,String channel,String orderNo,
                                          String phone,String timeLimit,String penaltyDay,Integer state,String level) {
        PageHelper.startPage(page,rows);

        Map<String,Object> paramMap = new HashMap<String, Object>();

        paramMap.put("id", id);
        paramMap.put("urgeName", urgeName);
        paramMap.put("borrowName", borrowName);
        paramMap.put("channel", channel);
        paramMap.put("orderNo", orderNo);
        paramMap.put("phone", phone);
        paramMap.put("timeLimit", timeLimit);
        paramMap.put("penaltyDay", penaltyDay);
        paramMap.put("state", state);
        paramMap.put("level", level);

        List<UrgeOrderInfo> urgeOrderInfoList = urgeOrderInfoMapper.selectAll(paramMap);

        PageInfo<UrgeOrderInfo> pageInfo = new PageInfo<UrgeOrderInfo>(urgeOrderInfoList);
        PageResult pageResult = new PageResult();

        pageResult.setTotal(pageInfo.getTotal());
        pageResult.setRows(urgeOrderInfoList);

        return pageResult;
    }

    /**
     * 查询所有待还款订单
     * @return
     */
    public List<UrgeOrderInfo> selectWaitRepayOrderList() {

        return urgeOrderInfoMapper.selectWaitRepayOrderList();
    }

    /**
     * 插入订单
     * @param urgeOrderInfo
     * @return
     */
    public int insert(UrgeOrderInfo urgeOrderInfo) {

        return urgeOrderInfoMapper.insert(urgeOrderInfo);
    }

    /**
     * 查询催收订单
     * @param map
     * @return
     */
    public UrgeOrderInfo queryUrgeOrder(Map<String, Object> map) {


        List<UrgeOrderInfo> urgeOrderInfoList = urgeOrderInfoMapper.selectAll(map);

        if (StringUtils.isEmpty(urgeOrderInfoList)){
            return new UrgeOrderInfo();
        } else {
            return urgeOrderInfoList.get(0);
        }
    }

    /**
     * 根据状态查询  订单
     * @param state
     * @return
     */
    public List<UrgeOrderInfo> selectByState(Integer state) {


        return urgeOrderInfoMapper.selectByState(state);
    }

    /**
     * 根据订单id  分配催收人员
     * @param urgeName
     * @param id
     * @return
     */
    public int updateUrgeNameById(Long urgeUserId, String urgeName, List<Long> id) {

        int num = 0;
        for (int i = 0; i < id.size(); i++) {

           /* num = this.updateUrgeNameById(urgeUserId, urgeName, id.get(i));
            num = this.updateUrgeStateById(new Integer(20), id.get(i));*/
            Map<String, Object> map = new HashMap<String, Object>();

            map.put("id",id.get(i));
            map.put("state",new Integer(20));
            map.put("urgeUserId",urgeUserId);
            map.put("urgeName",urgeName);

            urgeOrderInfoMapper.updateSelective(map);
        }

        return num;
    }

    /**
     * 根据id修改 催收人员姓名
     * @param urgeName
     * @param id
     * @return
     */
   /* public int updateUrgeNameById(Long urgeUserId, String urgeName, Long id){

        return urgeOrderInfoMapper.updateUrgeNameById(urgeUserId, urgeName, id);
    }*/


    /**
     * 根据id  修改订单状态
     * @param state
     * @param id
     * @return
     */
    public int updateUrgeStateById(Integer state, Long id) {

        Map<String, Object> map = new HashMap<String, Object>();

        map.put("id",id);
        map.put("state",state);

        return urgeOrderInfoMapper.updateSelective(map);
    }

    /**
     * 修改订单信息
     * @param map
     * @return
     */
    public int updateSelective(Map<String, Object> map) {

        return urgeOrderInfoMapper.updateSelective(map);
    }
}
