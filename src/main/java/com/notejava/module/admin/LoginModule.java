package com.notejava.module.admin;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;

import com.notejava.utils.ParamsUtil;

@IocBean
public class LoginModule {
	@At("/login")
	@Ok("json")
	public Object login(String userName,String password){
		userName = ParamsUtil.getString(userName);
		password = ParamsUtil.getString(password);
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(userName,new Md5Hash(password,"notejava").toString());
		subject.login(token);
		subject.getSession().setAttribute("user", userName);
		return null;
	}
	
	@At("/logout")
	@Ok("Redirect:/login.jsp")
	public Object logout(){
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		return null;
	}
}
