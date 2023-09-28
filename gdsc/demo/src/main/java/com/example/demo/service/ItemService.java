package com.example.demo.service;

import com.example.demo.domain.Item;
import com.example.demo.dto.ItemDto;
import com.example.demo.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    // 의존성 생성자 주입
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public void saveItem(ItemDto itemDto) {
        Item item = itemDto.toEntity(); // itemDto를 item으로 변환

        itemRepository.save(item); // itemRepository를 통해 item을 저장
    }

    public ItemDto findItemById(Long id) {
        Item item = itemRepository.findById(id); // itemRepository를 통해 id에 해당하는 item을 찾아서 반환

        return ItemDto.builder()
                .id(item.getId())
                .name(item.getName())
                .count(item.getCount())
                .build(); // itemDto 반환
    }

    public List<ItemDto> findAllItem() {
        return itemRepository.findAll()
                .stream()
                .map(item -> {
                    return ItemDto.builder()
                            .id(item.getId())
                            .name(item.getName())
                            .count(item.getCount())
                            .build(); // itemDto 반환
                })
                .toList(); // itemRepository를 통해 모든 item을 찾아서 반환
    }

    public void updateItemById(Long id, ItemDto itemDto) {
        Item item = itemDto.toEntity(); // itemDto를 item으로 변환
        item.initId(id);

        itemRepository.updateById(id, item); // itemRepository를 통해 id에 해당하는 item을 찾아서 내용을 수정
    }

    public void deleteItemById(Long id) {
        itemRepository.deleteById(id); // itemRepository를 통해 id에 해당하는 item을 찾아서 삭제
    }

}
