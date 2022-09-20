package com.sparta.week04.controller;

import com.sparta.week04.dto.ItemDto;
import com.sparta.week04.utils.NaverShopSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class SearchController {

    private final NaverShopSearch naverShopSearch;

    // 상품 검색 목록 불러오기
    @GetMapping("/api/search")
    public List<ItemDto> searchPro(@RequestParam String query){

        String result = naverShopSearch.search(query);
        return naverShopSearch.fromJSONtoItems(result);
    }
}
