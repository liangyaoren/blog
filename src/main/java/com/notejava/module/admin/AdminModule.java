package com.notejava.module.admin;

import com.notejava.bean.BlogType;
import com.notejava.bean.Blogger;
import com.notejava.bean.Link;
import com.notejava.utils.ParamsUtil;
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

import javax.servlet.ServletContext;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		Sql sql = Sqls.create("SELECT DATE_FORMAT(createTime,'%Y-%m') AS createTime ,COUNT(*) AS blogCount  FROM blog GROUP BY DATE_FORMAT(createTime,'%Y-%m') ORDER BY DATE_FORMAT(createTime,'%Y-%m') DESC");
		//按类型分类
		Sql sql2 = Sqls.create("select t2.id,t2.name as typeName,count(t1.id) as blogCount from blog t1,blogType t2 where t1.typeId=t2.id group by t1.typeId order by t2.orderNo");

		sql.setCallback(new SqlCallback() {
			@Override
			public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
				List<Map<String,Object>> lists = new ArrayList<Map<String,Object>>();
				while(rs.next()){
					Map<String, Object> map = new HashMap<String, Object>();
					String createTime = rs.getString("createTime");
					Integer blogCount = rs.getInt("blogCount");
					map.put("createTime", createTime);
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
			typeMap.put(type.getId(), type.getName());
		}
		blogger.setPassword(null);
		List<Map<String,Object>> createTimeList = (List<Map<String, Object>>) sql.getResult();
		List<Map<String,Object>> typeNameList = (List<Map<String, Object>>) sql2.getResult();

		ServletContext servletContext = Mvcs.getServletContext();
		servletContext.setAttribute("createTimeList", createTimeList);
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
		
		Blogger user = dao.fetch(Blogger.class,Cnd.where("userName", "=", userName).and("password", "=", password));
		if(user==null){
			throw new RuntimeException("原用户或密码错误");
		}
		
		user.setPassword(newPassword);
		dao.update(user);
		return null;
	}
}
