package com.board.dao;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.board.domain.Criteria;
import com.board.domain.SurveyQuestionDTO;

@Repository
public class SurveyQuestionDAOImpl implements SurveyQuestionDAO {

	@Inject
	private SqlSession sqlSession;

	private static String namespace = "SurveyMapper";
	
	// 설문지 질문 조회
	@Override
	public List<SurveyQuestionDTO> list(Map map) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList(namespace + ".questionlist", map);
	}

	@Override
	public int listCount() throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace + ".listCount");
	}

	@Override
	public int questionCnt(Map map) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace + ".questionCnt", map);
	}

}
