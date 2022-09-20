package com.board.service;

import com.board.domain.MemberDTO;

public interface MemberService {
	
	// 회원가입
	public void register(MemberDTO dto) throws Exception;
	
	// 로그인
	public MemberDTO login(MemberDTO dto) throws Exception;
	
	// 아이디 중복 체크
	public int idchk(MemberDTO memberDTO) throws Exception;
}
