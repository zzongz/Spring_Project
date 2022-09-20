package com.board.dao;

import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class LikeDAOImpl implements LikeDAO {

	@Inject
	private SqlSession sqlSession;

	// 좋아요 확인
	@Override
	public int liketest(Map map) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("likeMapper.liketest", map);
	}

	// 좋아요 등록
	@Override
	public void likeinsert(Map map) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.insert("likeMapper.likeinsert", map);
	}

	// 좋아요 취소
	@Override
	public void likedelete(Map map) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.delete("likeMapper.likedelete", map);
	}
	
	
}
