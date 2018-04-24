package com.cn.lianshou.controllrer;

import com.cn.lianshou.common.util.Constant;
import com.cn.lianshou.common.util.ServletUtils;
import com.cn.lianshou.common.util.pagehelper.PageInfo;
import com.cn.lianshou.common.util.pagehelper.PageResult;
import com.cn.lianshou.entity.SystemConfig;
import com.cn.lianshou.entity.SystemUser;
import com.cn.lianshou.service.SystemConfigService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author: Wanghh
 * Date: 2018/4/12 13:49
 */
@Controller
public class SystemConfigController {

    @Autowired
    private SystemConfigService systemConfigService;

    @RequestMapping(value = "/sys/config/index")
    public String showIndex(){

        return "sys-config-list";
    }

    /**
     * 获取配置信息列表
     * @return
     */
    @RequestMapping(value = "/sys/config/list")
    @ResponseBody
    public PageResult showUserList(Integer page, Integer rows, HttpServletRequest request){

        String channel = request.getParameter("channel");
        String timeLimit = request.getParameter("timeLimit");
        String unit = request.getParameter("unit");
        String penaltyFee = request.getParameter("penaltyFee");
        String penaltyAmountMax = request.getParameter("penaltyAmountMax");
        String state = request.getParameter("state");

        PageHelper.startPage(page,rows);

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("channel", channel);
        paramMap.put("timeLimit", timeLimit);
        paramMap.put("unit", unit);
        paramMap.put("penaltyFee", penaltyFee);
        paramMap.put("penaltyAmountMax", penaltyAmountMax);
        paramMap.put("state", state);

        List<SystemConfig> systemConfigList = systemConfigService.query(paramMap);

        PageInfo<SystemConfig> pageInfo = new PageInfo<SystemConfig>(systemConfigList);

        PageResult pageResult = new PageResult();

        pageResult.setTotal(pageInfo.getTotal());
        pageResult.setRows(systemConfigList);

        return pageResult;
    }

    /**
     * 保存配置信息
     * @param channel 渠道名称
     * @param timeLimit 借款期限
     * @param unit 期限单位：0天，1月
     * @param penaltyFee 逾期利率
     * @param penaltyAmountMax 逾期罚金上限
     * @param state 是否启用 10-启用 20-禁用
     * @param response
     */
    @RequestMapping(value = "/save/sys/config")
    public void saveSysConfig(String channel,String timeLimit,String unit,String penaltyFee,String penaltyAmountMax,String state,HttpServletRequest request, HttpServletResponse response){

        HttpSession session = ((HttpServletRequest) request).getSession();

        SystemUser systemUser = (SystemUser) session.getAttribute("systemUser");

        Map<String, Object> result = new HashMap<String, Object>();

        if (StringUtils.isEmpty(channel)){
            result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
            result.put(Constant.RESPONSE_CODE_MSG, "渠道名称不能为空！");
            ServletUtils.writeToResponse(response,result);
        }
        if (StringUtils.isEmpty(timeLimit)){
            result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
            result.put(Constant.RESPONSE_CODE_MSG, "借款期限不能为空！");
            ServletUtils.writeToResponse(response,result);
        }
        if (StringUtils.isEmpty(unit)){
            result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
            result.put(Constant.RESPONSE_CODE_MSG, "期限单位不能为空！");
            ServletUtils.writeToResponse(response,result);
        }

        if (StringUtils.isEmpty(penaltyFee)){
            result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
            result.put(Constant.RESPONSE_CODE_MSG, "逾期利率不能为空！");
            ServletUtils.writeToResponse(response,result);
        }

        if (StringUtils.isEmpty(penaltyAmountMax)){
            result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
            result.put(Constant.RESPONSE_CODE_MSG, "逾期罚金上限不能为空！");
            ServletUtils.writeToResponse(response,result);
        }

        if (StringUtils.isEmpty(state)){
            result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
            result.put(Constant.RESPONSE_CODE_MSG, "可用状态不能为空！");
            ServletUtils.writeToResponse(response,result);
        }

        Map<String, Object> params = new HashMap<String, Object>();

        params.put("channel", channel);

        List<SystemConfig> systemConfigList = systemConfigService.query(params);

        if (systemConfigList.size() < 1){

            SystemConfig systemConfig = new SystemConfig();

            systemConfig.setChannel(channel);
            systemConfig.setTimeLimit(timeLimit);
            systemConfig.setUnit(unit);
            systemConfig.setPenaltyFee(penaltyFee);
            systemConfig.setPenaltyAmountMax(penaltyAmountMax);
            systemConfig.setState(state);
            systemConfig.setCreateTime(new Date());
            systemConfig.setUpdateTime(new Date());
            systemConfig.setUpdateUserId(systemUser.getId());

            //保存配置信息
            int i = systemConfigService.save(systemConfig);

            if (i == 1){
                //保存成功
                result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
                result.put(Constant.RESPONSE_CODE_MSG, "添加配置信息成功！");
            } else {
                //保存失败
                result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
                result.put(Constant.RESPONSE_CODE_MSG, "添加配置信息失败！");
            }
        } else {
            //该用户名已存在
            result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
            result.put(Constant.RESPONSE_CODE_MSG, "该配置信息已存在，请重新输入！");
        }

        ServletUtils.writeToResponse(response,result);
    }

    /**
     * 修改配置信息
     * @param timeLimit 借款期限
     * @param unit 期限单位：0天，1月
     * @param penaltyFee 逾期利率
     * @param penaltyAmoutMax 逾期罚金上限
     * @param state 是否启用 10-启用 20-禁用
     * @param response
     */
    @RequestMapping(value = "/update/sys/config")
    public void updateSysConfig(Long id,String timeLimit,String unit,String penaltyFee,String penaltyAmoutMax,String state,HttpServletResponse response){
        Map<String, Object> result = new HashMap<String, Object>();

        Map<String, Object> paramMap = new HashMap<String, Object>();

        paramMap.put("id",id);
        paramMap.put("timeLimit",timeLimit);
        paramMap.put("unit",unit);
        paramMap.put("penaltyFee",penaltyFee);
        paramMap.put("penaltyAmoutMax",penaltyAmoutMax);
        paramMap.put("state",state);

        int i = systemConfigService.updateSelective(paramMap);

        if (i == 1){
            result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
            result.put(Constant.RESPONSE_CODE_MSG, "配置信息修改成功！");
        } else {
            result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
            result.put(Constant.RESPONSE_CODE_MSG, "配置信息修改失败！");
        }
        ServletUtils.writeToResponse(response,result);
    }


}
