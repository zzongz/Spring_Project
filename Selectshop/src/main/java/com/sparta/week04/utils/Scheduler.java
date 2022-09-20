package com.sparta.week04.utils;

import com.sparta.week04.dto.ItemDto;
import com.sparta.week04.models.Product;
import com.sparta.week04.repository.ProductRepository;
import com.sparta.week04.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Component // 스프링이 필요 시 자동으로 생성하는 클래스 목록에 추가한다.
public class Scheduler {

    private final ProductRepository productRepository;
    private final ProductService productService;
    private final NaverShopSearch naverShopSearch;

    // 초 분 시 일 월 주
    @Scheduled(cron = "0 0 1 * * *")
    public void updatePrice() throws InterruptedException{
        System.out.println("가격 업데이트 실행");

        // 스케쥴러 업데이트 로직
        List<Product> productList = productRepository.findAll();
        for(int i=0; i<productList.size(); i++){
            // 1초에 한 상품씩 조회(Naver 제한)
            TimeUnit.SECONDS.sleep(1);
            Product p = productList.get(i);
            // i 번째 관심 상품의 제목으로 검색을 실행
            String title = p.getTitle();
            String resultString = naverShopSearch.search(title);
            // i 번째 관심 상품의 검색 결과 목록 중에서 첫 번째 결과를 꺼낸다
            List<ItemDto> itemDtoList = naverShopSearch.fromJSONtoItems(resultString);
            ItemDto itemDto = itemDtoList.get(0);
            // i 번째 관심 상품 정보를 업데이트
            Long id = p.getId();
            productService.updateBySearch(id, itemDto);
        }
    }
}
