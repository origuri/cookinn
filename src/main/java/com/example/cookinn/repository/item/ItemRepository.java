package com.example.cookinn.repository.item;

import com.example.cookinn.model.item.ItemDto;

public interface ItemRepository {
    // item 등록시 시리얼 넘버로 중복확인
    ItemDto selectItemByItemBarcode(Long itemBarcode);
    /*
     * item 등록하기
     * 파라미터 : itemDto
     * 특이사항 : itemId는 특정 제품의 시리얼넘버(또는 바코드넘버)를 사용함,
     *           즉, 자동으로 올라가는 pk가 아닌 입력하는 방식.
     *           이미 등록되어 있는 지 확인, 최대 등록 수량 확인.
     * 권한 : admin
     * */
    int insertItemByItemDto(ItemDto itemDto);
}
