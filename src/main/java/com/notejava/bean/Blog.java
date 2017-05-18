package com.notejava.bean;

import java.util.Date;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Table("blog")
public class Blog {
	@Id
	@Column("id")
	private Long id;
	@Column("title")
	private String title;
	@Column("summary")
	private String summary;
	@Column("createTime")
	private Date createTime;
	@Column("clicks")
	private Integer clicks;
	@Column("replys")
	private Integer replys;
	@Column("content")
	private String content;
	@Column("typeId")
	private Long typeId;

	@Column("keyWord")

	private String keyWord;

	private String contentNoTag;
	private String createTimeStr;
	private String typeName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getClicks() {
		return clicks;
	}

	public void setClicks(Integer clicks) {
		this.clicks = clicks;
	}

	public Integer getReplys() {
		return replys;
	}

	public void setReplys(Integer replys) {
		this.replys = replys;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getTypeId() {
		return typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public String getContentNoTag() {
		return contentNoTag;
	}

	public void setContentNoTag(String contentNoTag) {
		this.contentNoTag = contentNoTag;
	}

	public String getcreateTimeStr() {
		return createTimeStr;
	}

	public void setcreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
}
