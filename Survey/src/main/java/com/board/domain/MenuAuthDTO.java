package com.board.domain;

public class MenuAuthDTO {
	
	private int menu_seq;
	private String user_auth;
	
	public int getMenu_seq() {
		return menu_seq;
	}
	public void setMenu_seq(int menu_seq) {
		this.menu_seq = menu_seq;
	}
	public String getUser_auth() {
		return user_auth;
	}
	public void setUser_auth(String user_auth) {
		this.user_auth = user_auth;
	}
}
