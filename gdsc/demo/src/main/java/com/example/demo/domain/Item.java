package com.example.demo.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
public class Item {

    private Long id;
    private String name;
    private Long count;

    @Builder
    public Item(Long id, String name, Long count) {
        this.id = id;
        this.name = name;
        this.count = count;
    }

    // 의미가 불분명한 setter 대신 이해하기 쉬운 메소드 이름을 지어줘요. (기능은 setter와 동일)
    public void initId(Long id) {
        this.id = id;
    }

    public void updateName(String name) {
        this.name = name;
    }

    public void updateCount(Long count) {
        this.count = count;
    }

}

