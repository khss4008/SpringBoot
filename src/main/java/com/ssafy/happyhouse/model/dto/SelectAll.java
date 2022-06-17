package com.ssafy.happyhouse.model.dto;

public class SelectAll {
	private int nid;
	private String userName;
	private String title;
	private String content;
	private String regtime;
	
	public SelectAll(int nid, String userName, String title, String content, String regtime) {
		super();
		this.nid = nid;
		this.userName = userName;
		this.title = title;
		this.content = content;
		this.regtime = regtime;
	}
	
	public int getNid() {
		return nid;
	}
	public void setNid(int nid) {
		this.nid = nid;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRegtime() {
		return regtime;
	}
	public void setRegtime(String regtime) {
		this.regtime = regtime;
	}
	
	@Override
	public String toString() {
		return "JoinBoardAndUserInfo [nid=" + nid + ", userName=" + userName + ", title=" + title + ", content="
				+ content + ", regtime=" + regtime + "]";
	}
	
}
