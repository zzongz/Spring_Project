package com.board.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.board.dao.ReplyDAO;
import com.board.domain.ReplyDTO;

@Service
public class ReplyServiceImpl implements ReplyService {
	
	@Inject
	private ReplyDAO dao;
	
	@Override
	public List<ReplyDTO> readReply(int seq) throws Exception{
		return dao.readReply(seq);
	}

	@Override
	public void writeReply(ReplyDTO dto) throws Exception {
		
		dao.writeReply(dto);
	}

	@Override
	public void updateReply(ReplyDTO dto) throws Exception {
		// TODO Auto-generated method stub
		dao.updateReply(dto);
	}

	@Override
	public void deleteReply(ReplyDTO dto) throws Exception {
		// TODO Auto-generated method stub
		dao.deleteReply(dto);
	}

	@Override
	public ReplyDTO selectReply(int rseq) throws Exception {
		// TODO Auto-generated method stub
		return dao.selectReply(rseq);
	}
}