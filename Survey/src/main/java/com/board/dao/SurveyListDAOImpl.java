package com.board.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.board.domain.SurveyListDTO;

@Repository
public class SurveyListDAOImpl implements SurveyListDAO{

	@Inject
	private SqlSession sqlSession;

	private static String namespace = "SurveyMapper";
	
	// 설문 목록 조회
	@Override
	public List<SurveyListDTO> list() throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList(namespace + ".list");
	}

	// 설문 안내 조회
	@Override
	public SurveyListDTO info(int survey_seq) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace + ".info", survey_seq);
	}

}
