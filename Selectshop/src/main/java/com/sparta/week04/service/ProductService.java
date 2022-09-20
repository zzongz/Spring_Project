package com.sparta.week04.service;

import com.sparta.week04.dto.ItemDto;
import com.sparta.week04.dto.MypriceDto;
import com.sparta.week04.models.Product;
import com.sparta.week04.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;

    // 관심 상품 최저가 등록 업데이트
    @Transactional // DB 정보가 업데이트.
    public Long update(Long id, MypriceDto mypriceDto){

        Product product = productRepository.findById(id).orElseThrow(
                () -> new NullPointerException("해당 아이디가 없습니다.")
        );
        product.update(mypriceDto);

        return id;
    }

    // 스케쥴러 검색 업데이트
    @Transactional
    public void updateBySearch(Long id, ItemDto itemDto){

        Product product = productRepository.findById(id).orElseThrow(
                () -> new NullPointerException("해당 아이디가 없습니다.")
        );
        product.updateByItemDto(itemDto);
    }
}
