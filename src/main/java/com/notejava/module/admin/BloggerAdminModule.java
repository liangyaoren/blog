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
import java.util.Map;

@IocBean
@At("admin/blogger")
public class BloggerAdminModule {
	@Inject
	private Dao dao;
	
	@At("/info")
	@Ok("jsp:admin.blogger.info")
	public Object info(){
		HttpSession session = Mvcs.getHttpSession();
		Blogger blogger = dao.fetch(Blogger.class, Cnd.where("userName", "=", session.getAttribute("user")));
		blogger.setPassword(null);
		return blogger;
	}
	
	@At("/save")
	@Ok("json")
	public Object save(@Param("..") Map<String,Object> map){
		Long id = ParamsUtil.getLong(map.get("id"));
		Blogger blogger = dao.fetch(Blogger.class, id);
		blogger.setUserName(ParamsUtil.getString(map.get("userName")));
		blogger.setSign(ParamsUtil.getString(map.get("sign")));
		blogger.setProFile((ParamsUtil.getString(map.get("proFile"))));
		dao.update(blogger);
		return null;
	}
}
