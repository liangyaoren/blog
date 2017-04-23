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
import com.notejava.bean.Link;
import com.notejava.utils.PageUtil;
import com.notejava.utils.ParamsUtil;

@IocBean
@At("admin/link")
public class LinkAdminModule {
	@Inject
	private Dao dao;
	private static int PAGE_SIZE = 10;
	private static int PAGE_NO = 1;
	
	@At("/list")
	@Ok("jsp:admin.link.list")
	public Object list(Integer pageNo,Integer pageSize){
		Pager pager = new Pager();
		pager.setPageNumber(ParamsUtil.getInteger(pageNo, PAGE_NO));
		pager.setPageSize(PAGE_SIZE);
		int count = dao.count(Link.class);
		if(count<=0){
			return null;
		}
		List<Link> links = dao.query(Link.class, Cnd.orderBy().desc("orderNo"),pager);
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put("links", links);
		//resultMap.put("pageBar", PageUtil.getPageBar(pager.getPageNumber(), pager.getPageSize(), count));
		resultMap.put("pageBar", PageUtil.getPageMap(pager.getPageNumber(), pager.getPageSize(), count));
		return resultMap;
	}
	
	@At("/info")
	@Ok("json")
	public Object info(Long id){
		if(id==null){
			//添加操作
			return null;
		}
		Link link = dao.fetch(Link.class, id);
		return link;
	}
	
	@At("/save")
	@Ok("json")
	public Object save(@Param("..")Map<String,Object> map){
		String id = (String) map.get("id");
		String linkName = ParamsUtil.getString(map.get("linkName"));
		String linkUrl = ParamsUtil.getString(map.get("linkUrl"));
		Integer orderNo = ParamsUtil.getInteger(map.get("orderNo"));
		
		Link link = null;
		if(Strings.isNullOrEmpty(id)){
			link = new Link();
		}else{
			link = dao.fetch(Link.class, ParamsUtil.getLong(id));
		}
		
		link.setLinkName(linkName);
		link.setLinkUrl(linkUrl);
		link.setOrderNo(orderNo);
		
		if(Strings.isNullOrEmpty(id)){
			//添加
			dao.insert(link);
		}else{
			//修改
			dao.update(link);
		}
		return null;
	}
	
	@At("/delete")
	@Ok("json")
	public Object delete(Long id){
		dao.delete(Link.class,ParamsUtil.getLong(id));
		return null;
	}
}
