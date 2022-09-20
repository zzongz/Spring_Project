package com.board.service;

import java.util.List;

import com.board.domain.SurveyListDTO;

public interface SurveyListService {

	// 설문 목록 조회
	public List<SurveyListDTO> list() throws Exception;
	
	// 설문 안내 조회
	public SurveyListDTO info(int survey_seq) throws Exception;
}
