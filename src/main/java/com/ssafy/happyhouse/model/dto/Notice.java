package com.ssafy.happyhouse.model.dto;

public class Notice {
	private int nid;
	private String title;
	private String content;
	private String regtime;
	private String uid;
	 
	public Notice(int nid, String uid, String title, String content, String regtime) {
		super();
		this.nid = nid;
		this.uid = uid;
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
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
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
		return "Board [nid=" + nid + ", uid=" + uid + ", title=" + title + ", content=" + content + ", regtime="
				+ regtime + "]";
	}
	
}
