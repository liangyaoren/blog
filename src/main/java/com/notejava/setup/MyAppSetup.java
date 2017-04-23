package com.notejava.setup;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import javax.servlet.ServletContext;

import com.notejava.schedule.SyncBlog;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;
import org.nutz.mvc.NutConfig;
import org.nutz.mvc.Setup;

import com.notejava.bean.BlogType;
import com.notejava.bean.Blogger;
import com.notejava.bean.Link;

public class MyAppSetup implements Setup{

	@Override
	public void init(NutConfig nc) {
		Dao dao = nc.getIoc().get(Dao.class);
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
		
		ServletContext servletContext = nc.getServletContext();
		servletContext.setAttribute("releaseDateList", releaseDateList);
		servletContext.setAttribute("typeNameList", typeNameList);
		servletContext.setAttribute("linkList", linkList);
		servletContext.setAttribute("blogger", blogger);
		servletContext.setAttribute("blogTypes", typeMap);

		Timer timer = new Timer();
		timer.schedule(new SyncBlog(), new Date(), 1*60*60*1000);
	}

	@Override
	public void destroy(NutConfig nc) {
		
	}
}
