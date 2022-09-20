package com.board.service;

import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.board.dao.SurveyFinishDAO;

@Service
public class SurveyFinishServiceImpl implements SurveyFinishService {

	@Inject
	private SurveyFinishDAO dao;

	@Override
	public void write(Map map) throws Exception {
		// TODO Auto-generated method stub
		dao.write(map);
	}

}
