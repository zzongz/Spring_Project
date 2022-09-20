package com.sparta.week03.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class Memo extends Timestamped {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String contents;

    public Memo(String username, String contents){

        this.username = username;
        this.contents = contents;
    }

    public Memo(MemoDTO memoDto){

        this.username = memoDto.getUsername();
        this.contents = memoDto.getContents();
    }

    public void update(MemoDTO memoDto){

        this.username = memoDto.getUsername();
        this.contents = memoDto.getContents();
    }
}
