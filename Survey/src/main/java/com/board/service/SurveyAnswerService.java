package com.board.service;

import java.util.List;

import com.board.domain.SurveyAnswerDTO;


public interface SurveyAnswerService {

	public List<SurveyAnswerDTO> list(int survey_seq) throws Exception;
}
