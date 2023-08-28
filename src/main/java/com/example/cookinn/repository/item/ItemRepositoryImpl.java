package com.example.cookinn.repository.item;

import com.example.cookinn.model.item.ItemDto;
import com.example.cookinn.model.item.ItemSearchDto;
import com.example.cookinn.model.item.ItemUpdateDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ItemRepositoryImpl implements ItemRepository{
    private final SqlSession session;

    /*
     * item 등록하기
     * 파라미터 : itemDto
     * 특이사항 : itemId는 특정 제품의 시리얼넘버(또는 바코드넘버)를 사용함,
     *           즉, 자동으로 올라가는 pk가 아닌 입력하는 방식.
     *           이미 등록되어 있는 지 확인, 최대 등록 수량 확인 용으로 사용할 예정
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

    /*
     * 아이템 등록 시 또는 이름으로 검색 로직
     * 파라미터 : barcode, name, productOrigin, company, keep
     * 특이사항 : user, admin 모두 사용 가능하게 해야함.
     *           onChange 이벤트 사용
     *           name으로 할 때는 sql에서 like %이름% 사용
     * 권한 : user 이상
     * */
    @Override
    public List<ItemDto> selectItemByItemSearchDto(ItemSearchDto itemSearchDto) {
        List<ItemDto> itemDtoList = null;
        try{
            log.info("itemSearchDto -> {}", itemSearchDto);
            itemDtoList = session.selectList("selectItemByItemSearchDto", itemSearchDto);
            log.info("레파지토리 selectItemByItemSearchDto 사이즈 -> {}", itemDtoList);
        }catch (Exception e){
            log.error("레파지토리 selectItemByItemSearchDto 에러 -> {}",e.getMessage());
        }
        return itemDtoList;
    }
    /*
     * 아이템 수정 로직
     * 파라미터 : name, productOrigin, unit, price, quantity, notice, keep, itemStatus, updatedTime
     * 권한 : admin
     * */
    @Override
    public int updateItemByItemDto(ItemUpdateDto itemUpdateDto) {
        int result = 0;
        try {
            result = session.update("updateItemByItemDto", itemUpdateDto);
            log.info("레파지토리 updateItemByItemDto result -> {}",result);
        }catch (Exception e){
            log.error("레파지토리 updateItemByItemDto 에러 -> {}",e.getMessage());

        }
        return result;
    }

    /*
     * 하나의 제품을 가져오는 상세정보
     * 수정할 때 필요함.
     * 파라미터 : itemId
     * 권한 user 이상
     * */
    @Override
    public ItemDto selectItemDetailByItemId(Long itemId) {
        ItemDto itemDto = null;
        try{
            itemDto = session.selectOne("selectItemDetailByItemId", itemId);
            log.info("레파지토리 selectItemDetailByItemId itemDto -> {}",itemDto);
        }catch (Exception e){
            log.info("레파지토리 selectItemDetailByItemId 에러 -> {}",e.getMessage());

        }
        return itemDto;
    }

}
