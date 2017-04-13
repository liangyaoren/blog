package com.notejava.utils;

public class PageBean {
	//指定的是页面参数
	private int pageNo;
	private int pageSize;
	//查询数据库
	private int total;
	//计算得出
	private int pageCount;
	private int beginIndex;
	private int endIndex;

	public PageBean(int pageNo,int pageSize,int total){
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		this.total = total;
		//计算总页码
		this.pageCount = (total + pageSize - 1)/pageSize;
		//如果总页数不超过10则全部显示
		if(pageCount<10){
			this.beginIndex = 1;
			this.endIndex = pageCount;
		}
		//如果总页数超过10
		else{
			this.beginIndex = this.pageNo-4;
			this.endIndex = this.pageNo+5;
			//如果首页小于1
			if(this.beginIndex < 1){
				this.beginIndex = 1;
				this.endIndex = 10;
			}
			//如果尾页超过总页数
			if(this.endIndex > pageCount){
				this.beginIndex = this.endIndex-9;
				this.endIndex = pageCount;
			}
		}
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getBeginIndex() {
		return beginIndex;
	}

	public void setBeginIndex(int beginIndex) {
		this.beginIndex = beginIndex;
	}

	public int getEndIndex() {
		return endIndex;
	}

	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}
}
