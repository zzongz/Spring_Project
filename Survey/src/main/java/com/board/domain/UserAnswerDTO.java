package com.board.domain;

import java.util.Date;

public class UserAnswerDTO {
	
	private int seq;
	private String userid;
	private int survey_seq;
	private int question_seq;
	private String answer;
	private Date regdate;
	
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
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
	public int getSurvey_seq() {
		return survey_seq;
	}
	public void setSurvey_seq(int survey_seq) {
		this.survey_seq = survey_seq;
	}
	public int getQuestion_seq() {
		return question_seq;
	}
	public void setQuestion_seq(int question_seq) {
		this.question_seq = question_seq;
	}

	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	
}