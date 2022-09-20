package com.board.domain;

import java.util.Date;

public class BoardDTO {
	
	int seq;
	String subject;
	String content;
	String name;
	Date regist_date;
	int read_count;
	int groupId;
	int parentId;
	String ORG_FILE_NAME;
	String typeid;
	
	public String getTypeid() {
		return typeid;
	}
	public void setTypeid(String typeid) {
		this.typeid = typeid;
	}
	public String getORG_FILE_NAME() {
		return ORG_FILE_NAME;
	}
	public void setORG_FILE_NAME(String oRG_FILE_NAME) {
		ORG_FILE_NAME = oRG_FILE_NAME;
	}
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getRegist_date() {
		return regist_date;
	}
	public void setRegist_date(Date regist_date) {
		this.regist_date = regist_date;
	}
	public int getRead_count() {
		return read_count;
	}
	public void setRead_count(int read_count) {
		this.read_count = read_count;
	}
	
	@Override
	public String toString() {
		return "BoardDTO [seq=" + seq + ", subject=" + subject + ", content=" + content + ", name=" + name
				+ ", regist_date=" + regist_date + ", read_count=" + read_count + ", groupId=" + groupId + ", parentId="
				+ parentId + ", ORG_FILE_NAME=" + ORG_FILE_NAME + ", typeid=" + typeid + "]";
	}
	
}