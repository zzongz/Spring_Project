package com.board.domain;

import java.util.Date;

public class ReplyDTO {
	
	private int seq;
	private int rseq;
	private String content;
	private String writer;
	private Date regist_date;
	
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public int getRseq() {
		return rseq;
	}
	public void setRseq(int rseq) {
		this.rseq = rseq;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public Date getRegist_date() {
		return regist_date;
	}
	public void setRegist_date(Date regist_date) {
		this.regist_date = regist_date;
	}
	
	@Override
	public String toString() {
		return "ReplyDTO [seq=" + seq + ", rseq=" + rseq + ", content=" + content + ", writer=" + writer + ", regist_date="
				+ regist_date + "]";
	}
	
}