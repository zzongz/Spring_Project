package com.board.domain;

import java.util.Date;

public class LikeDTO {
	
	private int seq;
	private String userid;
	private int board_seq;
	private Date regdate;
	
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public int getBoard_seq() {
		return board_seq;
	}
	public void setBoard_seq(int board_seq) {
		this.board_seq = board_seq;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	@Override
	public String toString() {
		return "LikeDTO [seq=" + seq + ", userid=" + userid + ", board_seq=" + board_seq + ", regdate=" + regdate + "]";
	}
}