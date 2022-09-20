package com.sparta.week03.controller;

import com.sparta.week03.domain.Memo;
import com.sparta.week03.domain.MemoDTO;
import com.sparta.week03.repository.MemoRepository;
import com.sparta.week03.service.MemoService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemoController {

    private final MemoRepository memoRepository;
    private final MemoService memoService;

    // 메모 전체 조회
    @GetMapping("/api/memos")
    public List<Memo> selectAll(){
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime yesterday = LocalDateTime.now().minusDays(1);
        return memoRepository.findAllByModifiedAtBetweenOrderByModifiedAtDesc(yesterday, now);

    }

    // 메모 쓰기
    @PostMapping("/api/memos")
    public Memo insert(@RequestBody MemoDTO memoDto){
        Memo memo = new Memo(memoDto);
        return memoRepository.save(memo);
    }

    // 메모 삭제
    @DeleteMapping("/api/memos/{id}")
    public Long deleteOne(@PathVariable Long id){
        memoRepository.deleteById(id);
        return id;
    }

    // 메모 수정
    @PutMapping("/api/memos/{id}")
    public Long update(@PathVariable Long id, @RequestBody MemoDTO memoDto){
        memoService.update(id, memoDto);
        return id;
    }
}