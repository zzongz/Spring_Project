package com.board.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.board.dao.MemberDAO;
import com.board.domain.MemberDTO;

@Service
public class MemberServiceImpl implements MemberService {

	@Inject
	MemberDAO dao;
	
	@Override
	public void register(MemberDTO dto) throws Exception {
		// TODO Auto-generated method stub
		dao.register(dto);
	}

	@Override
	public MemberDTO login(MemberDTO dto) throws Exception {
		// TODO Auto-generated method stub
		return dao.login(dto);
	}

	@Override
	public int idchk(MemberDTO memberDTO) throws Exception {
		// TODO Auto-generated method stub
		int result = dao.idchk(memberDTO);
		
		return result;
	}
	
}
