package com.notejava.module.admin;

import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.notejava.bean.Blogger;
import com.notejava.utils.ParamsUtil;

@IocBean
@At("admin/blogger")
public class BloggerAdminModule {
	@Inject
	private Dao dao;
	
	@At("/info")
	@Ok("jsp:admin.blogger.info")
	public Object info(){
		Subject subject = SecurityUtils.getSubject();
		String userName = (String) subject.getPrincipal();
		Blogger blogger = dao.fetch(Blogger.class, Cnd.where("userName", "=", userName));
		blogger.setPassword(null);
		return blogger;
	}
	
	@At("/save")
	@Ok("json")
	public Object save(@Param("..") Map<String,Object> map){
		Long id = ParamsUtil.getLong(map.get("id"));
		Blogger blogger = dao.fetch(Blogger.class, id);
		blogger.setUserName(ParamsUtil.getString(map.get("userName")));
		blogger.setNickName(ParamsUtil.getString(map.get("nickName")));
		blogger.setSign(ParamsUtil.getString(map.get("sign")));
		blogger.setProFile((ParamsUtil.getString(map.get("proFile"))));
		blogger.setImageName(ParamsUtil.getString(map.get("imageName")));
		dao.update(blogger);
		return null;
	}
}
