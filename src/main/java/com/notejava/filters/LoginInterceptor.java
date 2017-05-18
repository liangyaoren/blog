package com.notejava.filters;

import org.nutz.aop.InterceptorChain;
import org.nutz.aop.MethodInterceptor;
import org.nutz.mvc.Mvcs;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by yaoren on 2017/5/16.
 */
public class LoginInterceptor implements MethodInterceptor {
    @Override
    public void filter(InterceptorChain interceptorChain) throws Throwable {
        HttpSession session = Mvcs.getHttpSession(false);
        HttpServletResponse resp = Mvcs.getResp();
        Object user = session.getAttribute("user");
        if (user == null){
            resp.sendRedirect("/login.jsp");
            return;
        }
        interceptorChain.doChain();
    }
}
