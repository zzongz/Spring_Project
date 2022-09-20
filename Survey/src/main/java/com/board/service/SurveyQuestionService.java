package com.board.service;

import java.util.List;
import java.util.Map;

import com.board.domain.SurveyQuestionDTO;


public interface SurveyQuestionService {

	public List<SurveyQuestionDTO> list(Map map) throws Exception;
	
	public int listCount() throws Exception;
	
	public int questionCnt(Map map) throws Exception;
}
