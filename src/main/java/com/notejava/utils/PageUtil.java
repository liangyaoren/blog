package com.notejava.utils;

import java.util.HashMap;
import java.util.Map;

public class PageUtil {

	public static Map<String,Integer> getPageMap(int pageNo,int pageSize,int total){
		int pageCount = 0;
		int beginIndex = 0;
		int endIndex = 0;
		pageCount = (total + pageSize - 1)/pageSize;

		if(pageCount<5){
			beginIndex = 1;
			endIndex = pageCount;
		}else{
			beginIndex = pageNo-2;
			endIndex = pageNo+2;
			//如果首页小于1
			if(beginIndex < 1){
				beginIndex = 1;
				endIndex = 5;
			}
			//如果尾页超过总页数
			if(endIndex > pageCount){
				beginIndex = pageCount-4;
				endIndex = pageCount;
			}
		}

		Map<String,Integer> pageMap = new HashMap<>();
		pageMap.put("beginIndex", beginIndex);
		pageMap.put("endIndex", endIndex);
		pageMap.put("pageCount", endIndex);
		pageMap.put("currentPage", pageNo);
		return pageMap;
	}

	public static String getPageBar(int pageNo,int pageSize,int total){
		int pageCount = 0;
		int beginIndex = 0;
		int endIndex = 0;
		pageCount = (total + pageSize - 1)/pageSize;
		//如果总页数不超过10则全部显示
		if(pageCount<10){
			beginIndex = 1;
			endIndex = pageCount;
		}else{
			beginIndex = pageNo-4;
			endIndex = pageNo+5;
			//如果首页小于1
			if(beginIndex < 1){
				beginIndex = 1;
				endIndex = 10;
			}
			//如果尾页超过总页数
			if(endIndex > pageCount){
				beginIndex = pageCount-9;
				endIndex = pageCount;
			}
		}
		
		StringBuffer pageBar = new StringBuffer();
		
		
		if(pageNo!=beginIndex){
			pageBar.append("<li><a href='javascript:void(0)' onclick='gotoPage(1,"+pageSize+")'>首页</a></li>");
			pageBar.append("<li><a href='javascript:void(0)' onclick='gotoPage("+(pageNo-1)+","+pageSize+")'>上一页</a></li>");
		}
		
		for(int i=beginIndex;i<=endIndex;i++){
			if(i==pageNo){
				pageBar.append("<li class='active'><a onclick='gotoPage("+i+","+pageSize+")'>"+i+"</a></li>");
			}else{
				pageBar.append("<li><a href='javascript:void(0)' onclick='gotoPage("+i+","+pageSize+")'>"+i+"</a></li>");
			}
		}
		
		if(pageNo!=endIndex){
			pageBar.append("<li><a href='javascript:void(0)' onclick='gotoPage("+(pageNo+1)+","+pageSize+")'>下一页</a></li>");
			pageBar.append("<li><a href='javascript:void(0)' onclick='gotoPage("+pageCount+","+pageSize+")'>尾页</a></li>");
		}
		
		
		return pageBar.toString();
	}
	/**
	public static String getPageBarWithClickName(int pageNo,int pageSize,int total,String clickName){
		int pageCount = 0;
		int beginIndex = 0;
		int endIndex = 0;
		pageCount = (total + pageSize - 1)/pageSize;
		//如果总页数不超过10则全部显示
		if(pageCount<10){
			beginIndex = 1;
			endIndex = pageCount;
		}else{
			beginIndex = pageNo-4;
			endIndex = pageNo+5;
			//如果首页小于1
			if(beginIndex < 1){
				beginIndex = 1;
				endIndex = 10;
			}
			//如果尾页超过总页数
			if(endIndex > pageCount){
				beginIndex = endIndex-9;
				endIndex = pageCount;
			}
		}
		
		StringBuffer pageBar = new StringBuffer();
		
		
		if(pageNo!=beginIndex){
			pageBar.append("<li><a href='javascript:void(0)' onclick='"+clickName+"(1,"+pageSize+")'>首页</a></li>");
			pageBar.append("<li><a href='javascript:void(0)' onclick='"+clickName+"("+(pageNo-1)+","+pageSize+")'>上一页</a></li>");
		}
		
		for(int i=beginIndex;i<=endIndex;i++){
			if(i==pageNo){
				pageBar.append("<li class='active'><a onclick='"+clickName+"("+i+","+pageSize+")'>"+i+"</a></li>");
			}else{
				pageBar.append("<li><a href='javascript:void(0)' onclick='"+clickName+"("+i+","+pageSize+")'>"+i+"</a></li>");
			}
		}
		
		if(pageNo!=endIndex){
			pageBar.append("<li><a href='javascript:void(0)' onclick='"+clickName+"("+(pageNo+1)+","+pageSize+")'>下一页</a></li>");
			pageBar.append("<li><a href='javascript:void(0)' onclick='"+clickName+"("+pageCount+","+pageSize+")'>尾页</a></li>");
		}
		
		
		return pageBar.toString();
	}
	
	public static void main(String[] args) {
		String pageBar = getPageBar(11,11,1000);
		System.out.println(pageBar);
	}*/
}
