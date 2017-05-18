package com.notejava.bean;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Table("link")
public class Link {
	@Id
	@Column("id")
	private Long id; // 编号
	@Column("name")
	private String name; // 链接名称
	@Column("url")
	private String url; // 链接地址
	@Column("orderNo")
	private Integer orderNo; // 排序序号 从小到大排序

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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
}
