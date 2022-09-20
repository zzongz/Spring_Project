package com.board.domain;

import java.util.Date;

public class SurveyAnswerDTO {
	
	private int answer_seq;
	private int survey_seq;
	private int question_seq;
	private String content;
	private Date regdate;
	
	private boolean flag;	// 체크 유무 값 체크
	
	public int getAnswer_seq() {
		return answer_seq;
	}
	public void setAnswer_seq(int answer_seq) {
		this.answer_seq = answer_seq;
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	
	
}
