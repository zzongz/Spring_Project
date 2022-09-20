package com.board.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.board.dao.UserAnswerDAO;
import com.board.domain.UserAnswerDTO;

@Service
public class UserAnswerServiceImpl implements UserAnswerService {

	@Inject
	private UserAnswerDAO dao;
	
	@Override
	public void write(Map map) throws Exception {
		// TODO Auto-generated method stub
		dao.write(map);
	}

	@Override
	public int list(Map map) throws Exception {
		// TODO Auto-generated method stub
		return dao.list(map);
	}

	@Override
	public void update(Map map) throws Exception {
		// TODO Auto-generated method stub
		dao.update(map);
	}

	@Override
	public void delete(Map map) throws Exception {
		// TODO Auto-generated method stub
		dao.delete(map);
	}

	@Override
	public int answerCnt(Map map) throws Exception {
		// TODO Auto-generated method stub
		return dao.answerCnt(map);
	}

	@Override
	public int checkCnt(Map map) throws Exception {
		// TODO Auto-generated method stub
		return dao.checkCnt(map);
	}

}
