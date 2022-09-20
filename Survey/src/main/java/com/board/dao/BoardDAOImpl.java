package com.board.dao;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.board.domain.BoardDTO;
import com.board.domain.Criteria;
import com.board.domain.NoticeDTO;
import com.board.domain.SearchCriteria;

@Repository
public class BoardDAOImpl implements BoardDAO {
	
	@Inject
	private SqlSession sqlSession;
	
	private static String namespace = "com.board.mappers.boardmap";

	// 게시물 목록 조회
	@Override
	public List<BoardDTO> list(SearchCriteria scri) throws Exception {
		return sqlSession.selectList(namespace + ".list", scri);
	}
	
	//통합 검색 리스트
	@Override
	public List<BoardDTO> alllist(SearchCriteria scri) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList(namespace + ".alllist", scri);
	}
	
	
	// 게시물 총합
	public int listCount(SearchCriteria scri) throws Exception{
		return sqlSession.selectOne(namespace + ".listCount", scri);
	}
	
	// 게시물 등록
	@Override
	public void write(BoardDTO boardDTO) throws Exception{
		sqlSession.insert(namespace + ".insert", boardDTO);
	}
	
	// 게시물 조회
	@Override
	public BoardDTO read(int seq) throws Exception{
		return sqlSession.selectOne(namespace + ".read", seq);
	}

	// 게시물 수정
	@Override
	public void update(BoardDTO boardDTO) throws Exception {
		sqlSession.update(namespace + ".update", boardDTO);
	}

	// 게시물 삭제
	@Override
	public void delete(int seq) throws Exception {
		sqlSession.delete(namespace + ".delete", seq);
	}

	// 조회수
	@Override
	public void boardHit(int seq) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.update(namespace + ".boardHit", seq);
	}
	
	// 공지사항 조회
	@Override
	public List<NoticeDTO> noticelist() throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList(namespace + ".noticelist");
	}
	
	// 답글 등록
	@Override
	public void answerwrite(BoardDTO boardDTO) throws Exception{
		sqlSession.insert(namespace + ".answerwrite", boardDTO);
	}
	
	// 첨부 파일 등록
	@Override
	public void insertFile(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.insert(namespace + ".insertFile", map);
	}

	// 첨부 파일 조회
	@Override
	public List<Map<String, Object>> selectFileList(int seq) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList(namespace + ".selectFileList", seq);
	}

	// 첨부 파일 다운
	@Override
	public Map<String, Object> selectFileInfo(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace + ".selectFileInfo", map);
	}

	// 첨부 파일 수정
	@Override
	public void updateFile(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		
		sqlSession.update(namespace + ".updateFile", map);
	}

	@Override
	public void downloadHit(Map map) throws Exception {

		sqlSession.update(namespace + ".downloadHit", map);		
	}

	@Override
	public List<BoardDTO> filelist(SearchCriteria scri) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList(namespace + ".filelist", scri);
	}

}