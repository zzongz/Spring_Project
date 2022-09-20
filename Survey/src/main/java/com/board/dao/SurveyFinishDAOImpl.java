package com.board.dao;

import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class SurveyFinishDAOImpl implements SurveyFinishDAO {

	@Inject
	private SqlSession sqlSession;

	private static String namespace = "SurveyMapper";
	
	@Override
	public void write(Map map) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.insert(namespace + ".useranswerfinish", map);
		
	}

}
