package com.board.domain;

import java.util.Date;

public class SurveyListDTO {
	
	private int survey_seq;
	private String subject;
	private String userid;
	private Date regdate;
	private String startdate;
	private String enddate;
	private String awarddate;
	private String progress;
	private String host;
	private String target;
	private int question_count;
	
	public String getStartdate() {
		return startdate;
	}
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}
	public String getEnddate() {
		return enddate;
	}
	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
	public String getAwarddate() {
		return awarddate;
	}
	public void setAwarddate(String awarddate) {
		this.awarddate = awarddate;
	}
	public int getSurvey_seq() {
		return survey_seq;
	}
	public void setSurvey_seq(int survey_seq) {
		this.survey_seq = survey_seq;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	public String getProgress() {
		return progress;
	}
	public void setProgress(String progress) {
		this.progress = progress;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public int getQuestion_count() {
		return question_count;
	}
	public void setQuestion_count(int question_count) {
		this.question_count = question_count;
	}
	
	
}
