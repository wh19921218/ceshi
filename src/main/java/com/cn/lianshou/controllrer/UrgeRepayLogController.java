package com.cn.lianshou.controllrer;

import com.cn.lianshou.common.util.Constant;
import com.cn.lianshou.common.util.ServletUtils;
import com.cn.lianshou.entity.UrgeRepayLog;
import com.cn.lianshou.service.UrgeRepayLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * FileName: com.cn.lianshou.controllrer.UrgeRepayLogController.java
 * Author: Wanghh
 * Date: 2018/3/31 17:52
 */
@Controller
public class UrgeRepayLogController {

    @Autowired
    private UrgeRepayLogService urgeRepayLogService;

    /**
     *
     * @param urgeOrderId 催收订单id
     * @param urgeWay 催收方式
     * @param urgeResult 催收结果
     * @param promiseTime 承诺还款时间
     * @param remark 描述
     */
    @RequestMapping(value = "/save/urge/info")
    public void insert(String urgeOrderId, String urgeWay, String urgeResult, String promiseTime, String remark,
                       String orderNo, String urgeUserId, HttpServletResponse response){

        UrgeRepayLog urgeRepayLog = new UrgeRepayLog();

        urgeRepayLog.setUrgeOrderId(urgeOrderId);
        urgeRepayLog.setOrderNo(orderNo);
        urgeRepayLog.setPromiseTime(promiseTime);
        urgeRepayLog.setRemark(remark);
        urgeRepayLog.setUrgeUserId(urgeUserId);
        urgeRepayLog.setWay(urgeWay);
        urgeRepayLog.setState(urgeResult);


        int i = urgeRepayLogService.insert(urgeRepayLog);

        Map<String, Object> result = new HashMap<String, Object>();

        if (i == 1){
            result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
            result.put(Constant.RESPONSE_CODE_MSG, "保存催收记录成功！");
            ServletUtils.writeToResponse(response,result);
        } else {
            result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
            result.put(Constant.RESPONSE_CODE_MSG, "保存催收记录失败！");
            ServletUtils.writeToResponse(response,result);
        }
    }






}
