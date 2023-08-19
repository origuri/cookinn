package com.example.cookinn.controller.item;

import com.example.cookinn.model.httpResponse.HttpResponseDto;
import com.example.cookinn.model.httpResponse.HttpResponseInfo;
import com.example.cookinn.model.item.ItemDto;
import com.example.cookinn.repository.item.ItemRepository;
import com.example.cookinn.service.item.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    * 아이템 등록이나 검색할 때 searchDto에 바코드하고 이름 해서 onchange이벤트로 검사하는 로직 만들자
    * itemRepository.selectItemByItemBarcode(itemDto.getItemBarcode()); 이거 조금 수정해서 사용하자.
    * */
}
