package com.example.cookinn.service.item;


import com.example.cookinn.model.item.ItemDto;
import com.example.cookinn.repository.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
@Slf4j
public class ItemService {
    private final ItemRepository itemRepository;

    /*
     * item 등록하기
     * 파라미터 : itemDto
     * 특이사항 : itemId는 특정 제품의 시리얼넘버(또는 바코드넘버)를 사용함,
     *           즉, 자동으로 올라가는 pk가 아닌 입력하는 방식.
     *           이미 등록되어 있는 지 확인, 최대 등록 수량 확인.
     * 권한 : admin
     * */
    public int addItemByItemDto(ItemDto itemDto) {
        if(itemDto.getQuantity() >= 1000 || itemDto.getCategoryLargeId() == 0 || itemDto.getCategoryMidId() == 0){
            return 2;
        }
        int result = itemRepository.insertItemByItemDto(itemDto);
        return result;
    }
}
