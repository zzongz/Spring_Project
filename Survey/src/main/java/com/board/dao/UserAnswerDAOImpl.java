package com.board.dao;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.board.domain.UserAnswerDTO;

@Repository
public class UserAnswerDAOImpl implements UserAnswerDAO{
	
	@Inject
	private SqlSession sqlSession;
	
	private static String namespace = "SurveyMapper";

	@Override
	public void write(Map map) throws Exception {
		
		sqlSession.insert(namespace + ".useranswerinsert", map);
	}

	@Override
	public int list(Map map) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace + ".useranswerCnt", map);
	}

	@Override
	public void update(Map map) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.update(namespace + ".useranswerupdate", map);
	}

	@Override
	public void delete(Map map) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.delete(namespace + ".useranswerdelete", map);
	}

	@Override
	public int answerCnt(Map map) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace + ".answerCnt", map);
	}

	@Override
	public int checkCnt(Map map) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace + ".checkCnt", map);
	}

}
