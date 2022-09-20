package com.board.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.board.domain.SurveyAnswerDTO;

@Repository
public class SurveyAnswerDAOImpl implements SurveyAnswerDAO {

	@Inject
	private SqlSession sqlSession;

	private static String namespace = "SurveyMapper";
	
	@Override
	public List<SurveyAnswerDTO> list(int survey_seq) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList(namespace + ".answerlist", survey_seq);
	}

}
