package com.example.cookinn.controller.store;

import com.example.cookinn.auth.PrincipalDetails;
import com.example.cookinn.model.store.StoreDto;
import com.example.cookinn.service.store.StoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cookinn")
@Slf4j
public class StoreController {

    private final StoreService storeService;

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
        /*
        *
        * store 등록부터 시작~
        *
        * */
        return null;
    }
}
