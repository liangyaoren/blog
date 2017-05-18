package com.notejava.module.admin;

import com.notejava.bean.Blogger;
import com.notejava.utils.ParamsUtil;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.Mvcs;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import javax.servlet.http.HttpSession;

@IocBean
public class LoginModule {

	@Inject
	private Dao dao;

	@At("/login")
	@Ok("json")
	public Object login(@Param("userName")String userName, @Param("password")String password){
		userName = ParamsUtil.getString(userName);
		password = ParamsUtil.getString(password);
		Blogger blogger = dao.fetch(Blogger.class, Cnd.where("userName", "=", userName));
		if (blogger.getPassword().equals(password)){
			HttpSession session = Mvcs.getHttpSession();
			session.setAttribute("user",blogger);
			return true;
		}
		return false;
	}
	
	@At("/logout")
	@Ok("Redirect:/login.jsp")
	public Object logout(){
		Mvcs.getHttpSession().invalidate();
		return null;
	}
}
