package com.notejava.bean;

import java.util.Date;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Table("comment")
public class Comment {
	@Id
	@Column("id")
	private Long id; // 编号
	@Column("userIp")
	private String userIp; // 用户IP
	@Column("content")
	private String content; // 评论内容
	@Column("blog")
	private Blog blog; // 被评论的博客
	@Column("commentDate")
	private Date commentDate; // 评论日期
	@Column("state")
	private Integer state; // 审核状态  0 待审核 1 审核通过 2 审核未通过
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserIp() {
		return userIp;
	}
	public void setUserIp(String userIp) {
		this.userIp = userIp;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Blog getBlog() {
		return blog;
	}
	public void setBlog(Blog blog) {
		this.blog = blog;
	}
	public Date getCommentDate() {
		return commentDate;
	}
	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
}
