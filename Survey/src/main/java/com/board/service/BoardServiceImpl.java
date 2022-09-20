package com.board.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.board.dao.BoardDAO;
import com.board.domain.BoardDTO;
import com.board.domain.Criteria;
import com.board.domain.NoticeDTO;
import com.board.domain.SearchCriteria;
import com.board.util.FileUtils;

@Service
public class BoardServiceImpl implements BoardService {

	@Resource(name="fileUtils")
	private FileUtils fileUtils;
	
	@Inject
	private BoardDAO dao;

	// 게시물 목록 조회
	@Override
	public List<BoardDTO> list(SearchCriteria scri) throws Exception {
		return dao.list(scri);
	}
	
	// 게시물 총 갯수
	public int listCount(SearchCriteria scri) throws Exception{
		return dao.listCount(scri);
	}

	//게시글 작성
	@Override
	public void write(BoardDTO boardDTO, MultipartHttpServletRequest bRequest) throws Exception {
		dao.write(boardDTO);
		
		List<Map<String, Object>> list = fileUtils.parseInsertFileInfo(boardDTO, bRequest);
		//int size = list.size();
		for(int i=0; i<list.size(); i++) {
			System.out.println(list.toString());
			dao.insertFile(list.get(i));
		}
	}
	
	// 게시물 조회
	@Transactional(isolation = Isolation.READ_COMMITTED)
	@Override
	public BoardDTO read(int seq) throws Exception{
		
		return dao.read(seq);
	}

	// 게시물 수정
	@Override
	public void update(BoardDTO boardDTO,
						String[] files,
						String[] fileNames,
						MultipartHttpServletRequest bRequest) throws Exception {
		
		dao.update(boardDTO);
		
		List<Map<String, Object>> list = fileUtils.parseUpdateFileInfo(boardDTO, files, fileNames, bRequest);
		
		Map<String, Object> tempMap = null;
		int size = list.size();
		for(int i = 0; i < size; i++) {
			tempMap = list.get(i);
			if(tempMap.get("IS_NEW").equals("Y")) {
				dao.insertFile(tempMap);
			}else {
				dao.updateFile(tempMap);
			}
		}
	}

	@Override
	public void delete(int seq) throws Exception {
		dao.delete(seq);
	}

	@Override
	public void boardHit(int seq) throws Exception {
		// TODO Auto-generated method stub
		dao.boardHit(seq);
	}

	@Override
	public List<NoticeDTO> noticelist() throws Exception {
		// TODO Auto-generated method stub
		return dao.noticelist();
	}
	
	@Override
	public void answerwrite(BoardDTO boardDTO) throws Exception {
		dao.answerwrite(boardDTO);
	}

	// 첨부 파일 조회
	@Override
	public List<Map<String, Object>> selectFileList(int seq) throws Exception {
		// TODO Auto-generated method stub
		return dao.selectFileList(seq);
	}

	// 첨부 파일 다운
	@Override
	public Map<String, Object> selectFileInfo(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return dao.selectFileInfo(map);
	}

	@Override
	public void downloadHit(Map map) throws Exception {
		// TODO Auto-generated method stub
		dao.downloadHit(map);
	}

	@Override
	public List<BoardDTO> alllist(SearchCriteria scri) throws Exception {
		// TODO Auto-generated method stub
		return dao.alllist(scri);
	}

	@Override
	public List<BoardDTO> filelist(SearchCriteria scri) throws Exception {
		// TODO Auto-generated method stub
		return dao.filelist(scri);
	}

}