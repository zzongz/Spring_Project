package com.sparta.week04.models;

import com.sparta.week04.dto.ItemDto;
import com.sparta.week04.dto.MypriceDto;
import com.sparta.week04.dto.ProductDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor // 기본 생성자 생성.
@Entity // DB 테이블 역할.
public class Product extends Timestamped {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String image;

    @Column(nullable = false)
    private String link;

    // 최저가
    @Column(nullable = false)
    private int lprice;

    // 사용자가 지정한 최저가
    @Column(nullable = false)
    private int myprice;

    public Product(ProductDto productDto){
        this.title = productDto.getTitle();
        this.link = productDto.getLink();
        this.lprice = productDto.getLprice();
        this.image = productDto.getImage();
        this.myprice = 0;
    }

    public void update(MypriceDto mypriceDto){
        this.myprice = mypriceDto.getMyprice();
    }

    public void updateByItemDto(ItemDto itemDto){
        this.lprice = itemDto.getLprice();
    }
}
