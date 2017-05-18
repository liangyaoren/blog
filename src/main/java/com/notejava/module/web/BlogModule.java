package com.notejava.module.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.sql.Criteria;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.Mvcs;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;

import com.notejava.bean.Blog;
import com.notejava.utils.ParamsUtil;

@IocBean
@At("blog")
public class BlogModule {
	@Inject
	private Dao dao;
	private ServletContext servletContext = Mvcs.getServletContext();
	
	@At("/?")
	@Ok("jsp:web.blog.info")
	public Object info(Long id){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Blog blog = dao.fetch(Blog.class,ParamsUtil.getLong(id));
		Map<Long,String> blogTypes = (Map<Long, String>) servletContext.getAttribute("blogTypes");
		blog.setTypeName(blogTypes.get(blog.getTypeId()));
		resultMap.put("blog", blog);
		
		Pager pager = new Pager().setPageSize(1).setPageNumber(1);
		//查询上一页
		Criteria cri1 = Cnd.cri();
		cri1.where().andLT("id", id);
		cri1.getOrderBy().desc("id");
		List<Blog> prefixBlogs = dao.query(Blog.class, cri1,pager);
		if(!prefixBlogs.isEmpty()){
			Blog lastBlog = new Blog();
			lastBlog.setId(prefixBlogs.get(0).getId());
			lastBlog.setTitle(prefixBlogs.get(0).getTitle());
			resultMap.put("lastBlog", lastBlog);
		}
		
		//查询下一页
		Criteria cri2 = Cnd.cri();
		cri2.where().andGT("id", id);
		cri2.getOrderBy().asc("id");
		List<Blog> nextBlogs = dao.query(Blog.class, cri2,pager);
		if(!nextBlogs.isEmpty()){
			Blog nextBlog = new Blog();
			nextBlog.setId(nextBlogs.get(0).getId());
			nextBlog.setTitle(nextBlogs.get(0).getTitle());
			resultMap.put("nextBlog", nextBlog);
		}
		
		//阅读数加1
		Blog b = dao.fetch(Blog.class,id);
		Integer clickHit = b.getClicks();
		if(clickHit!=null){
			b.setClicks(clickHit+1);
		}else{
			b.setClicks(1);
		}
		dao.update(b);
		
		return resultMap;
	}
}
