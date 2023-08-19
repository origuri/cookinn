package com.example.cookinn.repository.item;

import com.example.cookinn.model.item.ItemDto;
import com.example.cookinn.model.item.ItemSearchDto;

import java.util.List;

public interface ItemRepository {

    /*
     * item 등록하기
     * 파라미터 : itemDto
     * 특이사항 : itemId는 특정 제품의 시리얼넘버(또는 바코드넘버)를 사용함,
     *           즉, 자동으로 올라가는 pk가 아닌 입력하는 방식.
     *           이미 등록되어 있는 지 확인, 최대 등록 수량 확인.
     * 권한 : admin
     * */
    int insertItemByItemDto(ItemDto itemDto);
    /*
     * 아이템 등록 시 또는 이름으로 검색 로직
     * 파라미터 : barcode, name, productOrigin, company, keep
     * 특이사항 : user, admin 모두 사용 가능하게 해야함.
     *           onChange 이벤트 사용
     *           name으로 할 때는 sql에서 like %이름% 사용
     * 권한 : user 이상
     * */
    List<ItemDto> selectItemByItemSearchDto(ItemSearchDto itemSearchDto);
}
