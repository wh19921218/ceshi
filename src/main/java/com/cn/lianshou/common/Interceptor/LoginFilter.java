package com.cn.lianshou.common.Interceptor;

import com.cn.lianshou.entity.SystemUser;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * FileName: com.cn.lianshou.common.Interceptor.LoginFilter.java
 * Author: Wanghh
 * Date: 2018/3/26 16:40
 */
public class LoginFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        // TODO Auto-generated method stub
        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse resp =(HttpServletResponse) response;
        HttpSession session = req.getSession();
        // 获得用户请求的URI
        String path = req.getRequestURI();
        // 从session取得已经登录验证的凭证
        SystemUser systemUser = (SystemUser) session.getAttribute("systemUser");
        // login.jsp页面无需过滤(根据自己项目的要求来)

        //也可以path.contains("/login.do")
        if(path.indexOf("/login.do") > -1 || path.indexOf("/file.do") > -1) {//注意：登录的接口不要过滤掉
            chain.doFilter(req, resp);
            return;
        } else {//如果不是/login.do进行过滤
            if (systemUser == null || "".equals(systemUser)) {
                // 跳转到登陆页面
                resp.sendRedirect("/login.do");
            } else {
                // 已经登陆,继续此次请求
                chain.doFilter(req, resp);
            }
        }
    }

    public void destroy() {

    }
}
