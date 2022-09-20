package com.board.dao;

import java.util.List;
import java.util.Map;

import com.board.domain.UserAnswerDTO;

public interface UserAnswerDAO {

	public void write(Map map) throws Exception;
	
	public int list(Map map) throws Exception;
	
	public void update(Map map) throws Exception;
	
	public void delete(Map map) throws Exception;
	
	public int answerCnt(Map map) throws Exception;
	
	public int checkCnt(Map map) throws Exception;
}
