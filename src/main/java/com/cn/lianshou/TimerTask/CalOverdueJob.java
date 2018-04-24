package com.cn.lianshou.TimerTask;

import com.cn.lianshou.common.util.DateUtil;
import com.cn.lianshou.common.util.SpringContextUtil;
import com.cn.lianshou.entity.SystemConfig;
import com.cn.lianshou.entity.UrgeOrderInfo;
import com.cn.lianshou.service.SystemConfigService;
import com.cn.lianshou.service.UrgeOrderInfoService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * FileName: 预期订单处理
 * Author: Wanghh
 * Date: 2018/4/4 15:42
 */
@Component
public class CalOverdueJob implements Job {


    /**
     * 预期订单处理
     */
    public void calOverdueOrder(){

        UrgeOrderInfoService urgeOrderInfoService = (UrgeOrderInfoService) SpringContextUtil.getBean("urgeOrderInfoService");

        SystemConfigService systemConfigService = (SystemConfigService) SpringContextUtil.getBean("SystemConfigService");

        Map<String,Object> map = new HashMap<String, Object>();

        map.put("id",1);

        //查询出所有待还款订单
        List<UrgeOrderInfo> list = urgeOrderInfoService.selectWaitRepayOrderList();

        //循环所有待还款订单
        for (UrgeOrderInfo urgeOrderInfo : list) {
            Map<String,Object> resultMap = new HashMap<String, Object>();
            resultMap.put("id", urgeOrderInfo.getId());
            //逾期天数
            int ii = DateUtil.getDayBetweenDay2(urgeOrderInfo.getRepayTime().toString(),new Date().toString());

            resultMap.put("penaltyDay", ii + "");
            //如果大于0，表示已逾期   修改订单信息
            if (ii > 0){
                //查看该订单的逾期收费参数
                //计算逾期费用
                //修改订单信息

                map.clear();
                map.put("channel", urgeOrderInfo.getChannel());
                List<SystemConfig> systemConfigList = systemConfigService.query(map);

                if (systemConfigList.get(0).getState().equals("0")){

                    String days = systemConfigList.get(0).getTimeLimit();
                    String penaltyFee = systemConfigList.get(0).getPenaltyFee();
                    String maxPenaltyAmount = systemConfigList.get(0).getPenaltyAmountMax();

                    String [] day = days.split(",");
                    String [] fee = penaltyFee.split(",");

                    //判断借款期限是：1天，2月
                    if (urgeOrderInfo.getUnit() == 1){
                        for (int i = 0; i < day.length; i++) {

                            if (day[i].equals(urgeOrderInfo.getTimeLimit())){

                                //最大罚息金额
                                BigDecimal bb = new BigDecimal(urgeOrderInfo.getAmount())
                                        .multiply(new BigDecimal(maxPenaltyAmount));
                                //当前罚息
                                BigDecimal cc =  new BigDecimal(urgeOrderInfo.getAmount())
                                        .multiply(new BigDecimal(day[i]))
                                        .multiply(new BigDecimal(fee[i]));
                                //判断罚息是否  超过最大限制
                                int iiii = new BigDecimal(urgeOrderInfo.getPenaltyAmout()).compareTo(bb);

                                //如果小于0，则没有超过最大罚息
                                if (iiii < 0){

                                    //判断罚息是否 大于最大金额
                                    if (cc.compareTo(bb) < 0) {

                                        resultMap.put("penaltyAmout", cc.toString().split("."));
                                    } else {
                                        //总的罚息金额，刚好 >= 最大罚息金额
                                        //修改逾期天数，把逾期金额改成最大逾期金额
                                        resultMap.put("penaltyAmout", bb.toString().split("."));
                                    }
                                } else { //逾期金额 已大于等于最大罚息金额，直接修改逾期天数，罚息金额为最大罚息金额

                                    //总的罚息金额，刚好 >= 最大罚息金额
                                    //修改逾期天数，把逾期金额改成最大逾期金额
                                    resultMap.put("penaltyAmout", bb.toString().split("."));
                                }
                            }
                        }
                    }
                    int num = urgeOrderInfoService.updateSelective(resultMap);

                }
            }

        }


        //systemConfigService.query(map);

    }


    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        calOverdueOrder();
    }
}
