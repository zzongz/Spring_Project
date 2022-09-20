package com.board.domain;

public class TotalDTO {
	private int seq;
	private int menu_seq;
	private String menu_name;
	private String myear;
	private String mmonth;
	private String mday;
	private String mtime;
	
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public int getMenu_seq() {
		return menu_seq;
	}
	public void setMenu_seq(int menu_seq) {
		this.menu_seq = menu_seq;
	}
	public String getMenu_name() {
		return menu_name;
	}
	public void setMenu_name(String menu_name) {
		this.menu_name = menu_name;
	}
	public String getMyear() {
		return myear;
	}
	public void setMyear(String myear) {
		this.myear = myear;
	}
	public String getMmonth() {
		return mmonth;
	}
	public void setMmonth(String mmonth) {
		this.mmonth = mmonth;
	}
	public String getMday() {
		return mday;
	}
	public void setMday(String mday) {
		this.mday = mday;
	}
	public String getMtime() {
		return mtime;
	}
	public void setMtime(String mtime) {
		this.mtime = mtime;
	}
	
	@Override
	public String toString() {
		return "TotalDTO [seq=" + seq + ", menu_seq=" + menu_seq + ", menu_name=" + menu_name + ", myear=" + myear
				+ ", mmonth=" + mmonth + ", mday=" + mday + ", mtime=" + mtime + "]";
	}
	
}
