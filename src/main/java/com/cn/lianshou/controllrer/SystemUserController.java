package com.cn.lianshou.controllrer;

import com.cn.lianshou.common.util.Constant;
import com.cn.lianshou.common.util.MD5;
import com.cn.lianshou.common.util.ServletUtils;
import com.cn.lianshou.common.util.pagehelper.PageInfo;
import com.cn.lianshou.common.util.pagehelper.PageResult;
import com.cn.lianshou.entity.SystemUser;
import com.cn.lianshou.service.SystemUserService;
import com.github.pagehelper.PageHelper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * FileName: com.cn.lianshou.controllrer.SystemUserController.java
 * Author: Wanghh
 * Date: 2018/3/22 14:06
 */
@Controller
public class SystemUserController {
    Logger logger = Logger.getLogger(SystemUserController.class);

    @Autowired
    private SystemUserService systemUserService;

    /**
     * 1、到登陆页面
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String loginIndex(){

        return "login";
    }

    /**
     * 2、用户登录
     * @param userName
     * @param password
     */
    @RequestMapping(value = "/user/login")
    public void login(String userName, String password, HttpServletResponse response,HttpServletRequest request){

        HttpSession session = ((HttpServletRequest) request).getSession();
        Map<String, Object> result = new HashMap<String, Object>();

        if (StringUtils.isEmpty(userName)){
            result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
            result.put(Constant.RESPONSE_CODE_MSG, "用户名不能为空！");
            ServletUtils.writeToResponse(response,result);
        }

        if (StringUtils.isEmpty(password)){
            result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
            result.put(Constant.RESPONSE_CODE_MSG, "登录密码不能为空！");
            ServletUtils.writeToResponse(response,result);
        }

        SystemUser systemUser = systemUserService.queryInfoByUserNameAndPassword(userName,password);


        if ( !StringUtils.isEmpty(systemUser)){

            session.setAttribute("systemUser", systemUser);

            result.put("URL", "/show/index.do");
            result.put(Constant.RESPONSE_DATA, systemUser);
            result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
            result.put(Constant.RESPONSE_CODE_MSG, "查询成功");

        } else {

            result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
            result.put(Constant.RESPONSE_CODE_MSG, "用户不存在或者密码错误！");
        }

        ServletUtils.writeToResponse(response,result);
    }

    /**
     * 3、主页
     * @return
     */
    @RequestMapping(value = "/show/index")
    public String showIndex(HttpServletRequest request){

        HttpSession session = ((HttpServletRequest) request).getSession();

        SystemUser systemUser = (SystemUser) session.getAttribute("systemUser");

        if (systemUser.getUserType().equals(0)){
            return "sys-index";
        } else {
            return "urge-index";
        }
    }

    /**
     * 4、新增用户 页面
     * @return
     */
    /*@RequestMapping("/show/add/user")
    public String showAddUserIndex(){

        return "add-user";
    }*/

    /**
     * 5、保存用户
     * @param userName
     * @param password
     * @param phone
     * @param userType
     */
    @RequestMapping(value = "/save/user")
    public void saveUser(String userName,String realName,String password,String phone,Integer userType,HttpServletResponse response){


        Map<String, Object> result = new HashMap<String, Object>();

        if (StringUtils.isEmpty(userName)){
            result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
            result.put(Constant.RESPONSE_CODE_MSG, "用户名不能为空！");
            ServletUtils.writeToResponse(response,result);
        }
        if (StringUtils.isEmpty(realName)){
            result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
            result.put(Constant.RESPONSE_CODE_MSG, "真实姓名不能为空！");
            ServletUtils.writeToResponse(response,result);
        }

        if (StringUtils.isEmpty(password)){
            result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
            result.put(Constant.RESPONSE_CODE_MSG, "登录密码不能为空！");
            ServletUtils.writeToResponse(response,result);
        }

        if (StringUtils.isEmpty(phone)){
            result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
            result.put(Constant.RESPONSE_CODE_MSG, "手机号码不能为空！");
            ServletUtils.writeToResponse(response,result);
        }

        if (StringUtils.isEmpty(userType)){
            result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
            result.put(Constant.RESPONSE_CODE_MSG, "用户类型不能为空！");
            ServletUtils.writeToResponse(response,result);
        }

        SystemUser systemUser = systemUserService.queryInfoByUserName(userName);

        if (StringUtils.isEmpty(systemUser)){
            //保存用户信息
            int i = systemUserService.saveUser(userName,realName,MD5.MD5(password),phone,userType);

            if (i == 1){
                //保存成功
                result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
                result.put(Constant.RESPONSE_CODE_MSG, "添加用户操作成功！");
            } else {
                //保存失败
                result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
                result.put(Constant.RESPONSE_CODE_MSG, "添加用户操作失败！");
            }
        } else {
            //该用户名已存在
            result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
            result.put(Constant.RESPONSE_CODE_MSG, "该用户名已存在，暂不能使用，请重新输入用户名！");
        }

        ServletUtils.writeToResponse(response,result);
    }


    /**
     * 6、查询用户 页面
     * @return
     */
    @RequestMapping("/user/index")
    public String showUserIndex(){

        return "sys-user-list";
    }

    /**
     * 7、获取用户列表
     * @return
     */
    @RequestMapping("/sys/user/list")
    @ResponseBody
    public PageResult showUserList(Integer page,Integer rows,HttpServletRequest request){

        String phone = request.getParameter("phone");
        String userType = request.getParameter("userType");
        String state = request.getParameter("state");

        PageHelper.startPage(page,rows);

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("phone",phone);
        paramMap.put("userType",userType);
        paramMap.put("state",state);

        List<SystemUser> userList = systemUserService.queryUserList(paramMap);

        PageInfo<SystemUser> pageInfo = new PageInfo<SystemUser>(userList);

        PageResult pageResult = new PageResult();

        pageResult.setTotal(pageInfo.getTotal());
        pageResult.setRows(userList);

        return pageResult;
    }

    /**
     * 8、修改用户状态
     * @param id
     * @param state
     * @param response
     */
    @RequestMapping("/update/user/state")
    public void updateUserState(Long id,String state,HttpServletResponse response){
        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, Object> paramMap = new HashMap<String, Object>();

        paramMap.put("id",id);
        paramMap.put("state",state);

        int i = systemUserService.updateSelective(paramMap);

        if (i == 1){
            result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
            result.put(Constant.RESPONSE_CODE_MSG, "用户状态修改成功！");
        } else {
            result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
            result.put(Constant.RESPONSE_CODE_MSG, "用户状态修改失败！");
        }
        ServletUtils.writeToResponse(response,result);
    }

    /**
     * 9、跳转到修改密码页面
     */
    /*@RequestMapping("/update/login/password/index.do")
    public String updateLoginPasswordIndex(HttpServletRequest request, Model model){
        HttpSession session = ((HttpServletRequest) request).getSession();
        SystemUser systemUser = (SystemUser) session.getAttribute("systemUser");

        model.addAttribute("id",systemUser.getId());
        return "update-password";
    }*/

    /**
     * 10、修改登录密码
     * @param ypassword 原密码
     * @param zpassword 改后密码
     * @param request
     * @param response
     */
    @RequestMapping("/update/login/password")
    public void updatePassword(String ypassword,String zpassword,HttpServletRequest request,HttpServletResponse response){
        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        HttpSession session = ((HttpServletRequest) request).getSession();
        SystemUser systemUser = (SystemUser) session.getAttribute("systemUser");

        if (!systemUser.getPassword().equals(MD5.MD5(ypassword))){
            result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
            result.put(Constant.RESPONSE_CODE_MSG, "原密码错误，请确认后重新输入！");
            ServletUtils.writeToResponse(response,result);
        }

        paramMap.put("id",systemUser.getId());
        paramMap.put("password",MD5.MD5(zpassword));
        int i = systemUserService.updateSelective(paramMap);

        if (i == 1){
            result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
            result.put(Constant.RESPONSE_CODE_MSG, "密码修改成功，稍后请用新密码登陆！");
            session.removeAttribute("systemUser");
        } else {
            result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
            result.put(Constant.RESPONSE_CODE_MSG, "密码修改失败！");
        }
        ServletUtils.writeToResponse(response,result);
    }

    /**
     * 11、退出登录
     * @param request
     * @param response
     */
    @RequestMapping("/login/out")
    public void loginOut(HttpServletRequest request,HttpServletResponse response){
        HttpSession session = ((HttpServletRequest) request).getSession();

        try {
            session.removeAttribute("systemUser");
        } catch (Exception e) {
            logger.error("退出异常", e);
        }
        try {
            response.sendRedirect("/login.do");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
