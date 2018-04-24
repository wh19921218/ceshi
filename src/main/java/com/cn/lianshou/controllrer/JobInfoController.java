package com.cn.lianshou.controllrer;

import com.cn.lianshou.TimerTask.CalOverdueJob;
import com.cn.lianshou.TimerTask.QuartzManager;
import com.cn.lianshou.common.util.Constant;
import com.cn.lianshou.common.util.ServletUtils;
import com.cn.lianshou.common.util.pagehelper.PageInfo;
import com.cn.lianshou.common.util.pagehelper.PageResult;
import com.cn.lianshou.entity.JobInfo;
import com.cn.lianshou.entity.SystemUser;
import com.cn.lianshou.service.JobInfoService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author: Wanghh
 * Date: 2018/4/13 14:46
 */
@Controller
public class JobInfoController {

    @Autowired
    private JobInfoService jobInfoService;

    @RequestMapping(value = "/job/info/index")
    public String showIndex(){

        return "job-info-list";
    }

    /**
     * 获取 定时任务列表
     * @return
     */
    @RequestMapping(value = "/job/info/list")
    @ResponseBody
    public PageResult showUserList(Integer page, Integer rows, HttpServletRequest request){

        String name = request.getParameter("name");
        String code = request.getParameter("code");
        String state = request.getParameter("state");
        if ( !StringUtils.isEmpty(state)){
            if ( state.equals("-1")){
                state = "";
            }
        }

        PageHelper.startPage(page,rows);

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("name", name);
        paramMap.put("code", code);
        paramMap.put("state", state);

        List<JobInfo> jobInfoList = jobInfoService.query(paramMap);

        PageInfo<JobInfo> pageInfo = new PageInfo<JobInfo>(jobInfoList);

        PageResult pageResult = new PageResult();

        pageResult.setTotal(pageInfo.getTotal());
        pageResult.setRows(jobInfoList);

        return pageResult;
    }

    /**
     * 新增 定时任务信息
     * @param request
     * @param response
     */
    @RequestMapping(value = "/save/job/info")
    public void addJobInfo(HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> result = new HashMap<String, Object>();

        SystemUser systemUser = (SystemUser) request.getSession().getAttribute("systemUser");

        String code = request.getParameter("code");
        String cycle = request.getParameter("cycle");
        String jobService = request.getParameter("jobService");

        result.put("code",code);
        List<JobInfo> jobInfoList = jobInfoService.query(result);
        result.clear();

        if (jobInfoList.size() > 0){

            result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
            result.put(Constant.RESPONSE_CODE_MSG, "该任务标识已存在！");
        } else {
            JobInfo jobInfo = new JobInfo();

            jobInfo.setName(request.getParameter("name"));
            jobInfo.setCode(code);
            jobInfo.setCycle(cycle);
            jobInfo.setState(request.getParameter("state"));
            jobInfo.setJobService(jobService.toString() + ".class");

            if (StringUtils.isEmpty(systemUser)){
                jobInfo.setUpdateUserId(1L);
            } else {
                jobInfo.setUpdateUserId(systemUser.getId());
            }
            jobInfo.setCreateTime(new Date());
            jobInfo.setUpdateTime(new Date());
            int i =jobInfoService.save(jobInfo);
            if (i == 1){
                //每次新增任务时，要修改    CalOverdueJob.class;

                QuartzManager.addJob(code,CalOverdueJob.class,cycle,code);
                result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
                result.put(Constant.RESPONSE_CODE_MSG, "新增定时任务信息成功！");
            } else {
                result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
                result.put(Constant.RESPONSE_CODE_MSG, "新增定时任务信息修改失败！");
            }
        }
        ServletUtils.writeToResponse(response,result);
    }

    /**
     * 更新 定时任务信息
     * @param request
     * @param response
     */
    @RequestMapping(value = "/update/job/info")
    public void updateJobInfo(HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> result = new HashMap<String, Object>();

        SystemUser systemUser = (SystemUser) request.getSession().getAttribute("systemUser");
        Map<String, Object> paramsMap = new HashMap<String, Object>();

        String code = request.getParameter("code");
        String cycle = request.getParameter("cycle");

        paramsMap.put("id",request.getParameter("id"));
        paramsMap.put("name",request.getParameter("name"));
        paramsMap.put("code",code);
        paramsMap.put("cycle",cycle);
        if (StringUtils.isEmpty(systemUser)){
            paramsMap.put("updateUserId",1L);
        } else {
            paramsMap.put("updateUserId",systemUser.getId());
        }
        paramsMap.put("updateTime",new Date());

        int i =jobInfoService.updateSelective(paramsMap);
        if (i == 1){

            if ( !StringUtils.isEmpty(cycle)){
                QuartzManager.modifyJobTime(code, cycle);
            }
            result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
            result.put(Constant.RESPONSE_CODE_MSG, "更新定时任务信息成功！");
        } else {
            result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
            result.put(Constant.RESPONSE_CODE_MSG, "更新定时任务信息修改失败！");
        }
        ServletUtils.writeToResponse(response,result);
    }

    /**
     * 修改  定时任务状态
     * @param request
     * @param response
     */
    @RequestMapping(value = "/update/job/state")
    public void updateJobState(HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> result = new HashMap<String, Object>();

        SystemUser systemUser = (SystemUser) request.getSession().getAttribute("systemUser");
        Map<String, Object> paramsMap = new HashMap<String, Object>();

        String state = request.getParameter("state");
        String code = request.getParameter("code");


        paramsMap.put("id",request.getParameter("id"));
        paramsMap.put("code",code);
        paramsMap.put("state",state);
        if (StringUtils.isEmpty(systemUser)){
            paramsMap.put("updateUserId",1L);
        } else {
            paramsMap.put("updateUserId",systemUser.getId());
        }
        paramsMap.put("updateTime",new Date());

        int i =jobInfoService.updateSelective(paramsMap);
        if (i == 1){
            if (state.equals("0")){

                QuartzManager.startJobNow(code);
                result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
                result.put(Constant.RESPONSE_CODE_MSG, "启动定时任务信息成功！");
            } else {
                QuartzManager.removeJob(code);
                result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
                result.put(Constant.RESPONSE_CODE_MSG, "停止定时任务信息成功！");
            }
        } else {
            result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
            result.put(Constant.RESPONSE_CODE_MSG, "操作失败！");
        }
        ServletUtils.writeToResponse(response,result);
    }




}
