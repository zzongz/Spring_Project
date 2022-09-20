package com.sparta.week04.controller;

import com.sparta.week04.dto.MypriceDto;
import com.sparta.week04.dto.ProductDto;
import com.sparta.week04.models.Product;
import com.sparta.week04.repository.ProductRepository;
import com.sparta.week04.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ProductController {

    private final ProductRepository productRepository;
    private final ProductService productService;

    // 관심 상품 목록 불러오기
    @GetMapping("/api/products")
    public List<Product> readProducts(){

        return productRepository.findAll();
    }

    // 관심 상품 등록하기
    @PostMapping("/api/products")
    public Product createProducts(@RequestBody ProductDto productDto){

        Product product = new Product(productDto);
        return productRepository.save(product);
    }

    // 관심 가격 수정하기
    @PutMapping("/api/products/{id}")
    public void updateProducts(@PathVariable Long id, @RequestBody MypriceDto mypriceDto) {
        productService.update(id, mypriceDto);
    }

    // 관심 상품 삭제하기
    @DeleteMapping("/api/products/{id}")
    public void deleteProducts(@PathVariable Long id){
        productRepository.deleteById(id);
    }

}
