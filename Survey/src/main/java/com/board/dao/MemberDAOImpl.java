package com.board.dao;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.board.domain.MemberDTO;

@Repository
public class MemberDAOImpl implements MemberDAO {

	@Inject SqlSession sql;
	
	// 회원 가입
	@Override
	public void register(MemberDTO dto) throws Exception {
		// TODO Auto-generated method stub
		sql.insert("memberMapper.register", dto);
	}

	@Override
	public MemberDTO login(MemberDTO dto) throws Exception {
		// TODO Auto-generated method stub
		return sql.selectOne("memberMapper.login", dto);
	}

	// 아이디 중복 체크
	@Override
	public int idchk(MemberDTO memberDTO) throws Exception {
		// TODO Auto-generated method stub
		int result = sql.selectOne("memberMapper.idchk", memberDTO);
		
		return result;
	}

}
