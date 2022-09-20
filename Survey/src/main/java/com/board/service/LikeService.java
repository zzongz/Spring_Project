package com.board.service;

import java.util.Map;

public interface LikeService {
	
	// 좋아요 확인
	public int liketest(Map map) throws Exception;
	
	// 좋아요 등록
	public void likeinsert(Map map) throws Exception;
	
	// 좋아요 취소
	public void likedelete(Map map) throws Exception;
}
