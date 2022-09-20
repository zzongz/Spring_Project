package com.board.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.board.dao.SurveyListDAO;
import com.board.domain.SurveyListDTO;

@Service
public class SurveyListServiceImpl implements SurveyListService {

	@Inject
	private SurveyListDAO dao;
	
	// 설문 목록 조회
	@Override
	public List<SurveyListDTO> list() throws Exception {
		// TODO Auto-generated method stub
		return dao.list();
	}

	@Override
	public SurveyListDTO info(int survey_seq) throws Exception {
		// TODO Auto-generated method stub
		return dao.info(survey_seq);
	}

}
