package com.study.board.controller;

import com.study.board.domain.Board;
import com.study.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    // 글 목록 화면
    @GetMapping("/board/list")
    public String boardList(Model model,
                            String searchKeyword,
                            @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable){

        Page<Board> list = null;

        if(searchKeyword == null) {
            list = boardService.boardlist(pageable);
        }else{
            list = boardService.boardSearchList(searchKeyword, pageable);
        }

        int nowPage = list.getPageable().getPageNumber() + 1;
        int startPage = Math.max(nowPage - 4, 1);
        int endPage = Math.min(nowPage + 5, list.getTotalPages());

        model.addAttribute("list", list);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        return "boardlist";
    }

    // 글 쓰기 화면
    @GetMapping("/board/writeView")
    public String boardWriteView(){

        return "boardwrite";
    }

    // 글 쓰기 실행
    @PostMapping("/board/writepro")
    public String boardWritePro(Board board){

        boardService.write(board);
        return "redirect:/board/list";
    }

    // 글 상세보기 화면
    @GetMapping("/board/readView")
    public String boardReadView(Model model, Integer id){

        model.addAttribute("read", boardService.read(id));
        return "boardread";
    }

    // 글 삭제 실행
    @GetMapping("/board/deletepro")
    public String boardDeletePro(Integer id){

        boardService.delete(id);
        return "redirect:/board/list";
    }

    // 글 수정 화면
    @GetMapping("/board/updateView/{id}")
    public String boardUpdateView(@PathVariable Integer id, Model model){

        model.addAttribute("read", boardService.read(id));
        return "boardupdate";
    }
    
    // 글 수정 실행
    @PostMapping("/board/updatepro/{id}")
    public String boardUpdate(@PathVariable Integer id, Board board){

        Board updateBoard = boardService.read(id);
        updateBoard.setTitle(board.getTitle());
        updateBoard.setContent(board.getContent());
        boardService.write(updateBoard);

        return "redirect:/board/list";
    }
}