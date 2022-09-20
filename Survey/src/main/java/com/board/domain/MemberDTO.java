package com.board.domain;

import java.util.Date;

public class MemberDTO {
	private String userId;
	private String userPass;
	private String userName;
	private Date regDate;
	private String auth;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPass() {
		return userPass;
	}
	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public String getAuth() {
		return auth;
	}
	public void setAuth(String auth) {
		this.auth = auth;
	}
	
	@Override
	public String toString() {
		return "MemberDTO [아이디=" + userId + ", 비밀번호=" + userPass + ", 이름=" + userName + ", 가입일="
				+ regDate + ", 권한=" + auth + "]";
	}
}
