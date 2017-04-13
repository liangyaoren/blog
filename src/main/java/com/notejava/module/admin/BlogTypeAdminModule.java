package com.notejava.module.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.google.common.base.Strings;
import com.notejava.bean.BlogType;
import com.notejava.utils.PageUtil;
import com.notejava.utils.ParamsUtil;

@IocBean
@At("admin/blogType")
public class BlogTypeAdminModule {
	@Inject
	private Dao dao;
	private static int PAGE_SIZE = 10;
	private static int PAGE_NO = 1;
	
	@At("/list")
	@Ok("jsp:admin.blogType.list")
	public Object list(Integer pageNo,Integer pageSize){
		Pager pager = new Pager();
		pager.setPageNumber(ParamsUtil.getInteger(pageNo, PAGE_NO));
		pager.setPageSize(PAGE_SIZE);
		int count = dao.count(BlogType.class);
		if(count<=0){
			return null;
		}
		List<BlogType> blogTypes = dao.query(BlogType.class, Cnd.orderBy().desc("orderNo"),pager);
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put("blogTypes", blogTypes);
		resultMap.put("pageBar", PageUtil.getPageBar(pager.getPageNumber(), pager.getPageSize(), count));
		return resultMap;
	}
	
	@At("/info")
	@Ok("json")
	public Object info(Long id){
		if(id==null){
			//添加操作
			return null;
		}
		BlogType blogType = dao.fetch(BlogType.class, id);
		return blogType;
	}
	
	@At("/save")
	@Ok("json")
	public Object save(@Param("..")Map<String,Object> map){
		String id = (String) map.get("id");
		String typeName = ParamsUtil.getString(map.get("typeName"));
		Integer orderNo = ParamsUtil.getInteger(map.get("orderNo"));
		BlogType blogType = null;
		if(Strings.isNullOrEmpty(id)){
			blogType = new BlogType();
		}else{
			blogType = dao.fetch(BlogType.class, Long.valueOf(id));
		}
		blogType.setTypeName(typeName);
		blogType.setOrderNo(orderNo);
		if(Strings.isNullOrEmpty(id)){
			//添加
			dao.insert(blogType);
		}else{
			//修改
			dao.update(blogType);
		}
		return null;
	}
	
	@At("/delete")
	@Ok("json")
	public Object delete(Long id){
		dao.delete(BlogType.class,ParamsUtil.getLong(id));
		return null;
	}
}
