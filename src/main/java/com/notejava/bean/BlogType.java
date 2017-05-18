package com.notejava.bean;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Table("blogType")
public class BlogType {
	@Id
	@Column("id")
	private Long id;  // 编号
	@Column("name")
	private String name; // 博客类型名称
	@Column("orderNo")
	private Integer orderNo; // 排序  从小到大排序显示
	private Integer blogCount; // 数量
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
	public Integer getBlogCount() {
		return blogCount;
	}
	public void setBlogCount(Integer blogCount) {
		this.blogCount = blogCount;
	}
}
