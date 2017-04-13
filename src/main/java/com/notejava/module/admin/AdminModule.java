package com.notejava.module.admin;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.Mvcs;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.notejava.bean.BlogType;
import com.notejava.bean.Blogger;
import com.notejava.bean.Link;
import com.notejava.utils.ParamsUtil;

@IocBean
@At("admin")
public class AdminModule {
	@Inject
	private Dao dao;

	@At("/index")
	@Ok("jsp:admin.index")
	public Object index(){
		return null;
	}

	@At("/home")
	@Ok("jsp:admin.home")
	public Object home(){
		return null;
	}

	@At("/flush")
	@Ok("json")
	public Object flush(){
		//按日期分类
		Sql sql = Sqls.create("SELECT DATE_FORMAT(releaseDate,'%Y-%m') AS releaseDateStr ,COUNT(*) AS blogCount  FROM t_blog GROUP BY DATE_FORMAT(releaseDate,'%Y-%m') ORDER BY DATE_FORMAT(releaseDate,'%Y-%m') DESC");
		//按类型分类
		Sql sql2 = Sqls.create("select t2.id,t2.typeName,count(t1.id) as blogCount from t_blog t1,t_blogType t2 where t1.typeId=t2.id group by t1.typeId order by t2.orderNo");

		sql.setCallback(new SqlCallback() {
			@Override
			public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
				List<Map<String,Object>> lists = new ArrayList<Map<String,Object>>();
				while(rs.next()){
					Map<String, Object> map = new HashMap<String, Object>();
					String releaseDate = rs.getString("releaseDateStr");
					Integer blogCount = rs.getInt("blogCount");
					map.put("releaseDate", releaseDate);
					map.put("blogCount", blogCount);
					lists.add(map);
				}
				return lists;
			}
		});

		sql2.setCallback(new SqlCallback() {
			@Override
			public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
				List<Map<String,Object>> lists = new ArrayList<Map<String,Object>>();
				while(rs.next()){
					Map<String, Object> map = new HashMap<String, Object>();
					Long id = rs.getLong("id");
					String typeName = rs.getString("typeName");
					Integer blogCount = rs.getInt("blogCount");
					map.put("id", id);
					map.put("typeName", typeName);
					map.put("blogCount", blogCount);
					lists.add(map);
				}
				return lists;
			}
		});

		dao.execute(sql,sql2);
		//查询友情链接
		List<Link> linkList = dao.query(Link.class, Cnd.orderBy().desc("orderNo"));
		//查询博主信息
		Blogger blogger = dao.fetch(Blogger.class);
		//查询所有博客类型
		List<BlogType> blogTypes = dao.query(BlogType.class, null);
		//封装typeMap
		Map<Long, String> typeMap = new HashMap<Long, String>();
		for(BlogType type:blogTypes){
			typeMap.put(type.getId(), type.getTypeName());
		}
		blogger.setPassword(null);
		List<Map<String,Object>> releaseDateList = (List<Map<String, Object>>) sql.getResult();
		List<Map<String,Object>> typeNameList = (List<Map<String, Object>>) sql2.getResult();

		ServletContext servletContext = Mvcs.getServletContext();
		servletContext.setAttribute("releaseDateList", releaseDateList);
		servletContext.setAttribute("typeNameList", typeNameList);
		servletContext.setAttribute("linkList", linkList);
		servletContext.setAttribute("blogger", blogger);
		servletContext.setAttribute("blogTypes", typeMap);
		return null;
	}
	
	@At("/modifyPwd")
	@Ok("json")
	public Object modifyPwd(@Param("..")Map<String,Object> map){
		String userName = ParamsUtil.getString(map.get("userName"));
		String password = ParamsUtil.getString(map.get("password"));
		String newPassword = ParamsUtil.getString(map.get("newPassword"));
		
		Blogger user = dao.fetch(Blogger.class,Cnd.where("userName", "=", userName).and("password", "=", new Md5Hash(password,"notejava").toString()));
		if(user==null){
			throw new RuntimeException("原用户或密码错误");
		}
		
		user.setPassword(new Md5Hash(newPassword,"notejava").toString());
		dao.update(user);
		return null;
	}
}
