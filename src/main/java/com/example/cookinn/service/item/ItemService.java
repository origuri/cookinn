package com.example.cookinn.service.item;


import com.example.cookinn.model.item.ItemDto;
import com.example.cookinn.model.item.ItemSearchDto;
import com.example.cookinn.repository.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        String trimName = itemDto.getName().trim();
        itemDto.setName(trimName);
        int result = itemRepository.insertItemByItemDto(itemDto);
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
    public List<ItemDto> findItemListByItemSearchDto(ItemSearchDto itemSearchDto) {
        if(itemSearchDto.getName() != null){
            String trimName = itemSearchDto.getName().trim();
            itemSearchDto.setName(trimName);
        }else if(itemSearchDto.getProductOrigin() != null){
            String trimOrigin = itemSearchDto.getProductOrigin().trim();
            itemSearchDto.setProductOrigin(trimOrigin);
        }else if(itemSearchDto.getCompany() != null){
            String trimCompany = itemSearchDto.getCompany().trim();
            itemSearchDto.setCompany(trimCompany);
        }else if(itemSearchDto.getKeep() != null){
            String trimKeep = itemSearchDto.getKeep().trim();
            itemSearchDto.setKeep(trimKeep);
        }
        log.info("itemSearchDto -> {}", itemSearchDto);


        List<ItemDto> itemDtoList = itemRepository.selectItemByItemSearchDto(itemSearchDto);
        return itemDtoList;
    }
}
