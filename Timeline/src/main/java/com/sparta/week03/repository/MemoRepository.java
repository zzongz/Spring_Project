package com.sparta.week03.repository;

import com.sparta.week03.domain.Memo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface MemoRepository extends JpaRepository<Memo, Long> {

    // 24시간 내에 최신순으로 정렬(수정 날짜 기준 내림차순)
    List<Memo> findAllByModifiedAtBetweenOrderByModifiedAtDesc(LocalDateTime start, LocalDateTime end);
}