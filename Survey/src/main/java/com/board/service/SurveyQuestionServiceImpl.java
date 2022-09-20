package com.board.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.board.dao.SurveyQuestionDAO;
import com.board.domain.SurveyQuestionDTO;

@Service
public class SurveyQuestionServiceImpl implements SurveyQuestionService{

	@Inject
	private SurveyQuestionDAO dao;
	
	@Override
	public List<SurveyQuestionDTO> list(Map map) throws Exception {
		// TODO Auto-generated method stub
		return dao.list(map);
	}

	@Override
	public int listCount() throws Exception {
		// TODO Auto-generated method stub
		return dao.listCount();
	}

	@Override
	public int questionCnt(Map map) throws Exception {
		// TODO Auto-generated method stub
		return dao.questionCnt(map);
	}

}
