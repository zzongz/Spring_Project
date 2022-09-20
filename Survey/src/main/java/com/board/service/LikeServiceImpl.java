package com.board.service;

import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.board.dao.LikeDAO;

@Service
public class LikeServiceImpl implements LikeService{

	@Inject
	private LikeDAO dao;
	
	// 좋아요 확인
	@Override
	public int liketest(Map map) throws Exception {
		// TODO Auto-generated method stub
		return dao.liketest(map);
	}

	// 좋아요 등록
	@Override
	public void likeinsert(Map map) throws Exception {
		// TODO Auto-generated method stub
		dao.likeinsert(map);
	}

	// 좋아요 취소
	@Override
	public void likedelete(Map map) throws Exception {
		// TODO Auto-generated method stub
		dao.likedelete(map);
	}

}
