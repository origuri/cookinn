package com.example.cookinn.repository.item;

import com.example.cookinn.model.item.ItemDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ItemRepositoryImpl implements ItemRepository{
    private final SqlSession session;

    // item 등록시 시리얼 넘버로 중복확인
    @Override
    public ItemDto selectItemByItemBarcode(Long itemBarcode) {
        ItemDto itemDto = null;
        try{
            itemDto = session.selectOne("selectItemByItemBarcode", itemBarcode);
            log.info("레파지토리 selectItemByItemBarcode itemDto -> {}", itemDto);
        }catch (Exception e){
            log.error("레파지토리 selectItemByItemBarcode 에러 -> {}", e.getMessage());

        }
        return itemDto;
    }
    /*
     * item 등록하기
     * 파라미터 : itemDto
     * 특이사항 : itemId는 특정 제품의 시리얼넘버(또는 바코드넘버)를 사용함,
     *           즉, 자동으로 올라가는 pk가 아닌 입력하는 방식.
     *           이미 등록되어 있는 지 확인, 최대 등록 수량 확인.
     * 권한 : admin
     * */
    @Override
    public int insertItemByItemDto(ItemDto itemDto) {
        int result = 0;
        try{
            result = session.insert("insertItemByItemDto", itemDto);
            log.info("레파지토리 insertItemByItemDto result -> {}", result);
        }catch (Exception e){
            log.error("레파지토리 insertItemByItemDto 에러 -> {}", e.getMessage());

        }
        return result;
    }
}
