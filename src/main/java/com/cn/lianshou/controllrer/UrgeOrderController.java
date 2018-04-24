package com.cn.lianshou.controllrer;

import com.cn.lianshou.common.util.Constant;
import com.cn.lianshou.common.util.ExportExcelUtils;
import com.cn.lianshou.common.util.ServletUtils;
import com.cn.lianshou.common.util.pagehelper.PageResult;
import com.cn.lianshou.mapper.UrgeOrderInfoMapper;
import com.cn.lianshou.entity.BorrowUserInfo;
import com.cn.lianshou.entity.SystemUser;
import com.cn.lianshou.entity.UrgeOrderInfo;
import com.cn.lianshou.service.BorrowUserInfoService;
import com.cn.lianshou.service.SystemUserService;
import com.cn.lianshou.service.UrgeOrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * FileName: com.cn.lianshou.controllrer.UrgeOrderController.java
 * Author: Wanghh
 * Date: 2018/3/23 15:38
 */
@Controller
public class UrgeOrderController {

    @Autowired
    private UrgeOrderInfoService urgeOrderInfoService;
    @Autowired
    private SystemUserService systemUserService;
    @Autowired
    private BorrowUserInfoService borrowUserInfoService;
    @Autowired
    private UrgeOrderInfoMapper urgeOrderInfoMapper;

    /**
     * 跳转到  催收订单列表页
     * @return
     */
    @RequestMapping("/show/order/index")
    public String showUrgeOrderList(){

        return "urge-total-order-list";
    }
    /**
     * 跳转到  待分配订单页面
     * @return
     */
    @RequestMapping(value = "/show/await/allot/order")
    public String showAwaitAllotOrder(){

        return "await-allot-order-list";
    }
    /**
     * 跳转到  待催收订单页面
     * @return
     */
    @RequestMapping(value = "/show/await/urge/order")
    public String showAwaitUrgeOrder(HttpServletRequest request){

        HttpSession session = ((HttpServletRequest) request).getSession();

        SystemUser systemUser = (SystemUser) session.getAttribute("systemUser");

        //0为系统管理员  跳到不同的页面
        if (systemUser.getUserType().equals(0)){
            return "sys-await-urge-order-list";
        } else {
            return "urge-await-urge-order-list";
        }
    }
    /**
     * 跳转到  催收中订单页面
     * @return
     */
    @RequestMapping(value = "/show/urge/ing/order")
    public String showUrgeIngOrder(HttpServletRequest request){

        HttpSession session = ((HttpServletRequest) request).getSession();

        SystemUser systemUser = (SystemUser) session.getAttribute("systemUser");

        //0为系统管理员  跳到不同的页面
        if (systemUser.getUserType().equals(0)){
            return "sys-urge-ing-order-list";
        } else {
            return "urger-urge-ing-order-list";
        }
    }
    /**
     * 跳转到  催收成功订单页面
     * @return
     */
    @RequestMapping(value = "/show/urge/success/order")
    public String showUrgeSuccessOrder(){

        return "urge-success-order-list";
    }


    /**
     * 订单查询
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
    @RequestMapping(value = "/get/urge/order/list")
    @ResponseBody
    public PageResult getUrgeOrderList(Integer page, Integer rows, Long id, String urgeName, String borrowName, String channel, String orderNo, String phone,
                                       String timeLimit, String penaltyDay, Integer state, String level, HttpServletRequest request){

        HttpSession session = ((HttpServletRequest) request).getSession();

        SystemUser systemUser = (SystemUser) session.getAttribute("systemUser");

        if (systemUser.getUserType().equals(1)){
            urgeName = systemUser.getRealName();
        }

        PageResult pageResult =  urgeOrderInfoService.selectUrgeOrderList(page,rows,id,urgeName,borrowName,channel,orderNo,phone,timeLimit,penaltyDay,state,level);

        return pageResult;
    }

    /**
     * 修改订单状态
     */
    @RequestMapping(value = "/update/urge/order/state")
    public void updateUrgeOrderState(Long id,Integer state,HttpServletResponse response){

        Map<String, Object> result = new HashMap<String, Object>();

        int i = urgeOrderInfoService.updateUrgeStateById(state,id);

        if (i == 1){
            result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
            result.put(Constant.RESPONSE_CODE_MSG, "订单状态修改成功！");
        } else {
            result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
            result.put(Constant.RESPONSE_CODE_MSG, "订单状态修改失败！");
        }
        ServletUtils.writeToResponse(response,result);
    }


    /**
     * 根据订单id  查询对应的借款人信息
     * @param id
     */
    @RequestMapping(value = "/get/borrow/user/info/by/id")
    public void getBorrowUserInfoById(Long id, HttpServletResponse response){

        Map<String, Object> paramMap = new HashMap<String, Object>();
        Map<String, Object> result = new HashMap<String, Object>();

        paramMap.put("id", id);


        UrgeOrderInfo urgeOrderInfo = urgeOrderInfoService.queryUrgeOrder(paramMap);

        if (StringUtils.isEmpty(urgeOrderInfo)){
            //直接返回用户不存在

            result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
            result.put(Constant.RESPONSE_CODE_MSG, "该订单不存在！");
            ServletUtils.writeToResponse(response,result);
        }
        paramMap.clear();
        paramMap.put("id", urgeOrderInfo.getUrgeUserId());
        BorrowUserInfo borrowUserInfo = borrowUserInfoService.selectBorrowUserInfo(paramMap);

        result.put("borrowUserInfo",borrowUserInfo);
        result.put("urgeOrderInfo",urgeOrderInfo);
        result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
        result.put(Constant.RESPONSE_CODE_MSG, "订单分配成功！");
        ServletUtils.writeToResponse(response,result);
    }

    /**
     * 分配催收订单
     */
    @RequestMapping(value = "/borrow/repay/urge/allotUser")
    public void borrowRepayUrgeAllotUser(String borrowId, String userId, Integer state, HttpServletResponse response){

        List<UrgeOrderInfo> urgeOrderInfoList = urgeOrderInfoService.selectByState(new Integer(10));

        Map<String,Object> paramsMap = new HashMap<String,Object>();
        paramsMap.put("userType","1");
        paramsMap.put("state","0");

        List<SystemUser> systemUserList = systemUserService.queryUserList(paramsMap);

        int i = 0 ;
        int j = 0;
        //循环订单
        for (i = 0; i < urgeOrderInfoList.size()/2; i++) {
            //循环人的
            for (; j < systemUserList.size(); j++) {

                List<Long> longs = new ArrayList<Long>();

                longs.add(urgeOrderInfoList.get(i).getId());
                longs.add(urgeOrderInfoList.get(urgeOrderInfoList.size()-1-i).getId());

                urgeOrderInfoService.updateUrgeNameById(systemUserList.get(j).getId(), systemUserList.get(j).getRealName(), longs);

                j++;
                //如果催收员已经分完   开始新的一轮
                if ( j == systemUserList.size()){
                    j = 0;
                }
                break;
            }

        }
        Map<String, Object> result = new HashMap<String, Object>();

        result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
        result.put(Constant.RESPONSE_CODE_MSG, "订单分配成功！");
        ServletUtils.writeToResponse(response,result);
    }


    /**
     * 导出表格
     */
    @RequestMapping("/export/excel")
    public void exportExcel(HttpServletResponse response){

        ExportExcelUtils exportExcelUtils = new ExportExcelUtils();

        Map<String,Object> map = new HashMap<String, Object>();

        List<UrgeOrderInfo> urgeOrderInfoList = urgeOrderInfoMapper.selectAll(map);

        String title = "001";
        Object[] headers = {"订单id", "催收人姓名", "借款人姓名", "渠道", "订单编号", "借款人手机号", "借款金额", "借款时间",
                "借款期限", "期限单位", "预计还款时间", "逾期天数", "逾期金额", "订单状态", "逾期等级"};
        String[] params = {"id", "urgeName", "borrowName", "channel", "orderNo", "phone", "amount", "borrowTime",
                "timeLimit", "unit", "repayTime", "penaltyDay", "penaltyAmout", "state", "level"};

        exportExcelUtils.exportExcel(title,headers,urgeOrderInfoList,params,response);
    }


}
