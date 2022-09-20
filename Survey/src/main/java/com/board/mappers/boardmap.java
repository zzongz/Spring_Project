package com.board.mappers;

import java.util.List;

import com.board.domain.SurveyListDTO;

public interface boardmap {

	// 설문 목록 조회
	public List<SurveyListDTO> selectSurveyList() throws Exception;
	
	
	// 설문
}