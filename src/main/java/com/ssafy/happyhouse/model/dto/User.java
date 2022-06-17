package com.ssafy.happyhouse.model.dto;

public class User {
	private int uid;
	private String userId;
	private String userPwd;
	private String userName;
	private String userAddr;
	private String userTel;
	private String role;
	private String joinDate;
	
	public User() {
		// TODO Auto-generated constructor stub
	}

	public User(int uid, String userId, String userPwd, String userName, String userAddr, String userTel, String role,
			String joinDate) {
		super();
		this.uid = uid;
		this.userId = userId;
		this.userPwd = userPwd;
		this.userName = userName;
		this.userAddr = userAddr;
		this.userTel = userTel;
		this.role = role;
		this.joinDate = joinDate;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserAddr() {
		return userAddr;
	}

	public void setUserAddr(String userAddr) {
		this.userAddr = userAddr;
	}

	public String getUserTel() {
		return userTel;
	}

	public void setUserTel(String userTel) {
		this.userTel = userTel;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}

	@Override
	public String toString() {
		return "User [uid=" + uid + ", userId=" + userId + ", userPwd=" + userPwd + ", userName=" + userName
				+ ", userAddr=" + userAddr + ", userTel=" + userTel + ", role=" + role + ", joinDate=" + joinDate + "]";
	}
	
	
	
}
