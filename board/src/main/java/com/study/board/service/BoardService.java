package com.study.board.service;

import com.study.board.domain.Board;
import com.study.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    // 글 목록
    public Page<Board> boardlist(Pageable pageable){

        return boardRepository.findAll(pageable);
    }

    // 글 쓰기
    public void write(Board board) {

        boardRepository.save(board);
    }

    // 글 상세보기
    public Board read(Integer id){

        return boardRepository.findById(id).get();
    }

    // 글 삭제
    public void delete(Integer id){

        boardRepository.deleteById(id);
    }

    // 게시글 검색
    public Page<Board> boardSearchList(String searchKeyword, Pageable pageable){

        return boardRepository.findByTitleContaining(searchKeyword, pageable);
    }
}