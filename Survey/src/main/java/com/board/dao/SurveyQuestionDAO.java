package com.board.dao;

import java.util.List;
import java.util.Map;

import com.board.domain.Criteria;
import com.board.domain.SurveyQuestionDTO;

public interface SurveyQuestionDAO {
	
	// 설문지 질문 조회
	public List<SurveyQuestionDTO> list(Map map) throws Exception;

	public int listCount() throws Exception;

	public int questionCnt(Map map) throws Exception;
}
