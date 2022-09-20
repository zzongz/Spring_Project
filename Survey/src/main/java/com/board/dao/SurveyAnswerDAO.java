package com.board.dao;

import java.util.List;

import com.board.domain.SurveyAnswerDTO;

public interface SurveyAnswerDAO {

	// 설문지 질문 조회
	public List<SurveyAnswerDTO> list(int survey_seq) throws Exception;
}
