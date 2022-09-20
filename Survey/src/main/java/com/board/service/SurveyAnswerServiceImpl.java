package com.board.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.board.dao.SurveyAnswerDAO;
import com.board.domain.SurveyAnswerDTO;

@Service
public class SurveyAnswerServiceImpl implements SurveyAnswerService {

	@Inject
	private SurveyAnswerDAO dao;
	
	@Override
	public List<SurveyAnswerDTO> list(int survey_seq) throws Exception {
		// TODO Auto-generated method stub
		return dao.list(survey_seq);
	}

}
