package com.example.cookinn.controller.store;

import com.example.cookinn.auth.PrincipalDetails;
import com.example.cookinn.model.httpResponse.HttpResponseDto;
import com.example.cookinn.model.httpResponse.HttpResponseInfo;
import com.example.cookinn.model.store.StoreDto;
import com.example.cookinn.model.store.StoreUpdateDto;
import com.example.cookinn.repository.store.StoreRepository;
import com.example.cookinn.service.store.StoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cookinn")
@Slf4j
public class StoreController {

    private final StoreService storeService;
    private final StoreRepository storeRepository;

    /*
    * store 등록
    * 파라미터 : storeDto
    * 접근권한 : admin
    * */
    @PostMapping("/store/write")
    public ResponseEntity<?> storeAddByStoreDto(@RequestBody StoreDto storeDto/*,
                                                @AuthenticationPrincipal PrincipalDetails principalDetails*/){
        //storeDto.setAdminName(principalDetails.getUsername());
        storeDto.setAdminName("a");
        int result = storeRepository.insertStoreByStoreDto(storeDto);
        if(result == 1){
            return new ResponseEntity<>(new HttpResponseDto<>(HttpResponseInfo.OK.getStatusCode(), HttpResponseInfo.OK.getMessage()), HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(new HttpResponseDto<>(HttpResponseInfo.BAD_REQUEST.getStatusCode(), HttpResponseInfo.BAD_REQUEST.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
    /*
    * 전체 매장 조회
    * 파라미터 : x
    * 권한 : user 이상
    * */
    @GetMapping("/stores")
    public ResponseEntity<?> storeList(){
        List<StoreDto> storeDtos = storeRepository.selectStoreList();
        if(!storeDtos.isEmpty()){
            return new ResponseEntity<>(storeDtos, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(new HttpResponseDto<>(HttpResponseInfo.NOT_FOUND.getStatusCode(), HttpResponseInfo.NOT_FOUND.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    /*
    * 이름으로 매장 검색 조회
    * 파라미터 : storeName
    * 권한 : user 이상
    * */
    @GetMapping("/stores/search/{storeName}")
    public ResponseEntity<?> storeListByStoreName(@PathVariable("storeName") String storeName){
        log.info("이거확인 storeName-> {}", storeName);
        List<StoreDto> storeDtos = storeRepository.selectStoreListByStoreName(storeName);
        if(!storeDtos.isEmpty()){
            return new ResponseEntity<>(storeDtos, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(new HttpResponseDto<>(HttpResponseInfo.NOT_FOUND.getStatusCode(), HttpResponseInfo.NOT_FOUND.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    /*
    * 매장 단건 조회
    * 파라미터 : storeId
    * 권한 : user 이상
    * */
    @GetMapping("/stores/{storeId}")
    public ResponseEntity<?> storeDetailByStoreId(@PathVariable("storeId") Long storeId){
        StoreDto storeDto = storeRepository.selectStoreByStoreId(storeId);
        if(storeDto != null){
            return new ResponseEntity<>(storeDto, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(new HttpResponseDto<>(HttpResponseInfo.NOT_FOUND.getStatusCode(), HttpResponseInfo.NOT_FOUND.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    /*
    * 매장 정보 수정(폐점, 임시 휴점 수정 가능)
    * 파라미터 : storeUpdateDto
    * 권한 : admin
    * */
    @PutMapping("/store/{storeId}")
    public ResponseEntity<?> storeModifyByStoreUpdateDto(@PathVariable("storeId") Long storeId,
                                                         @RequestBody StoreUpdateDto storeUpdateDto){
        storeUpdateDto.setStoreId(storeId);
        int result = storeService.modifyStoreByStoreUpdateDto(storeUpdateDto);
        if(result == 1){
            return new ResponseEntity<>(new HttpResponseDto<>(HttpResponseInfo.OK.getStatusCode(), HttpResponseInfo.OK.getMessage()), HttpStatus.OK);
        }else if(result == 2){
            return new ResponseEntity<>(new HttpResponseDto<>(HttpResponseInfo.BAD_REQUEST.getStatusCode(), HttpResponseInfo.BAD_REQUEST.getMessage()), HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>(new HttpResponseDto<>(HttpResponseInfo.NOT_FOUND.getStatusCode(), HttpResponseInfo.NOT_FOUND.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

}
