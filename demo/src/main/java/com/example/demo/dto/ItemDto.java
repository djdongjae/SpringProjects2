package com.example.demo.dto;

import com.example.demo.domain.Item;
import lombok.Builder;
import lombok.Data;

@Data
public class ItemDto {

    private Long id;
    private String name;
    private Long count;

    @Builder
    public ItemDto(Long id, String name, Long count) {
        this.id = id;
        this.name = name;
        this.count = count;
    }

    // ItemDto -> Item
    public Item toEntity() {
        return Item.builder()
                .id(id)
                .name(name)
                .count(count)
                .build();
    }

}

