package com.board.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.board.domain.BoardDTO;
import com.board.domain.Criteria;
import com.board.domain.NoticeDTO;
import com.board.domain.SearchCriteria;

public interface BoardService {
	
	// 게시물 목록 조회
	public List<BoardDTO> list(SearchCriteria scri) throws Exception;
	
	// 통합 검색 리스트
	public List<BoardDTO> alllist(SearchCriteria scri) throws Exception;
	
	public List<BoardDTO> filelist(SearchCriteria scri) throws Exception;
	
	// 게시물 총합
	public int listCount(SearchCriteria scri) throws Exception;
	
	// 게시물 글 등록
	public void write(BoardDTO boardDTO, MultipartHttpServletRequest bRequest) throws Exception;
	
	// 게시물 글 조회
	public BoardDTO read(int seq) throws Exception;
	
	// 게시물 글 수정
	public void update(BoardDTO boardDTO,
						String[] files,
						String[] fileNames,
						MultipartHttpServletRequest bRequest) throws Exception;
	
	// 게시물 글 삭제
	public void delete(int seq) throws Exception;

	// 게시물 조회수 증가
	public void boardHit(int seq) throws Exception;
	
	public List<NoticeDTO> noticelist() throws Exception;
	
	public void answerwrite(BoardDTO boardDTO) throws Exception;
	
	// 첨부 파일 조회
	public List<Map<String, Object>> selectFileList(int seq) throws Exception;
	
	// 첨부 파일 다운
	public Map<String, Object> selectFileInfo(Map<String, Object> map) throws Exception;
	
	// 첨부 파일 다운로드 수
	public void downloadHit(Map map) throws Exception;
}