package com.example.cookinn.controller.item;

import com.example.cookinn.model.httpResponse.HttpResponseDto;
import com.example.cookinn.model.httpResponse.HttpResponseInfo;
import com.example.cookinn.model.item.ItemDto;
import com.example.cookinn.model.item.ItemSearchDto;
import com.example.cookinn.model.item.ItemUpdateDto;
import com.example.cookinn.repository.item.ItemRepository;
import com.example.cookinn.service.item.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cookinn")
@Slf4j
/*
* /item/** : 권한 admin
* /items/** : 권한 user 이상
* */
public class ItemController {
    private final ItemRepository itemRepository;
    private final ItemService itemService;

    /*
    * item 등록하기
    * 파라미터 : itemDto
    * 특이사항 : itemId는 특정 제품의 시리얼넘버(또는 바코드넘버)를 사용함,
    *           즉, 자동으로 올라가는 pk가 아닌 입력하는 방식.
    *           이미 등록되어 있는 지 확인, 최대 등록 수량 확인.
    * 권한 : admin
    * */
    @PostMapping("/item/write")
    public ResponseEntity<?> itemAddByItemDto(@RequestBody ItemDto itemDto){
        int result = itemService.addItemByItemDto(itemDto);
        if(result == 1){
            return new ResponseEntity<>(new HttpResponseDto<>(HttpResponseInfo.OK.getStatusCode(), HttpResponseInfo.OK.getMessage()), HttpStatus.CREATED);
        }else if(result == 2){
            return new ResponseEntity<>(new HttpResponseDto<>(HttpResponseInfo.BAD_REQUEST.getStatusCode(), HttpResponseInfo.BAD_REQUEST.getMessage()), HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>("이미 등록되어 있는 제품입니다.", HttpStatus.BAD_REQUEST);
        }
    }
    /*
    * 아이템 등록 시 또는 이름으로 검색 로직
    * 파라미터 : barcode, name, productOrigin, company, keep
    * 특이사항 : user, admin 모두 사용 가능하게 해야함.
    *           onChange 이벤트 사용
    *           name으로 할 때는 sql에서 like %이름% 사용
    * 권한 : user 이상
    * */
    @GetMapping("/items/search")
    public ResponseEntity<?> itemListByItemSearchDto(@RequestBody ItemSearchDto itemSearchDto){
        List<ItemDto> itemDtoList = itemService.findItemListByItemSearchDto(itemSearchDto);
        if(!itemDtoList.isEmpty()){
            return new ResponseEntity<>(itemDtoList, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(new HttpResponseDto<>(HttpResponseInfo.NOT_FOUND.getStatusCode(), HttpResponseInfo.NOT_FOUND.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    /*
    * 하나의 제품을 가져오는 상세정보
    * 수정할 때 필요함.
    * 파라미터 : itemId
    * 권한 user 이상
    * */
    @GetMapping("/items/{itemId}")
    public ResponseEntity<?> itemDetailByItemId(@PathVariable("itemId") Long itemId){
        ItemDto itemDto = itemRepository.selectItemDetailByItemId(itemId);
        if(itemDto == null){
            return new ResponseEntity<>(new HttpResponseDto<>(HttpResponseInfo.NOT_FOUND.getStatusCode(), HttpResponseInfo.NOT_FOUND.getMessage()), HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(itemDto, HttpStatus.OK);
        }
    }


    /*
    * 아이템 수정 로직
    * 파라미터 : name, productOrigin, unit, price, quantity, notice, keep, itemStatus, updatedTime
    * 권한 : admin
    * */
    @PutMapping("/item/{itemId}")
    public ResponseEntity<?> itemModifyByItemUpdateDto(@PathVariable("itemId") Long itemId,
                                                       @RequestBody @Valid ItemUpdateDto itemUpdateDto){
        itemUpdateDto.setItemId(itemId);
        int result = itemService.modifyItemByItemUpdateDto(itemUpdateDto);
        if(result == 1){
            return new ResponseEntity<>(new HttpResponseDto<>(HttpResponseInfo.OK.getStatusCode(), HttpResponseInfo.OK.getMessage()), HttpStatus.CREATED);
        }else if(result == 2){
            return new ResponseEntity<>(new HttpResponseDto<>(HttpResponseInfo.BAD_REQUEST.getStatusCode(), HttpResponseInfo.BAD_REQUEST.getMessage()), HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>("수정 실패", HttpStatus.BAD_REQUEST);
        }
    }

}
