package com.board.dao;

import java.util.List;

import com.board.domain.ReplyDTO;

public interface ReplyDAO {
	
	// 댓글 조회
	public List<ReplyDTO> readReply(int seq) throws Exception;
	
	// 댓글 작성
	public void writeReply(ReplyDTO dto) throws Exception;
	
	// 댓글 수정
	public void updateReply(ReplyDTO dto) throws Exception;
	
	// 댓글 삭제
	public void deleteReply(ReplyDTO dto) throws Exception;
	
	// 선택된 댓글 조회
	public ReplyDTO selectReply(int rseq) throws Exception;
}
