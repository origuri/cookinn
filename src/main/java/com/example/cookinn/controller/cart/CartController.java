package com.example.cookinn.controller.cart;

import com.example.cookinn.auth.PrincipalDetails;
import com.example.cookinn.model.cart.CartDto;
import com.example.cookinn.model.cart.CartInsertDto;
import com.example.cookinn.model.cart.CartUpdateDto;
import com.example.cookinn.model.httpResponse.HttpResponseDto;
import com.example.cookinn.model.httpResponse.HttpResponseInfo;
import com.example.cookinn.repository.cart.CartRepository;
import com.example.cookinn.service.cart.CartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("cookinn/")
/*
* 구매전 장바구니에 넣는 역할
* 발주 시스템이기 때문에 무조건 장바구니에 넣은 뒤 구매를 진행할 수 있음.
* */
public class CartController {
    private final CartService cartService;
    private final CartRepository cartRepository;
    /*
    * 장바구니 목록 가져오기
    * 파라미터 : x
    * 권한 : user
    * */
    @GetMapping("/cart")
    public ResponseEntity<?> cartListByMemberId(/*@AuthenticationPrincipal PrincipalDetails principalDetails*/){
        //Long memberId = principalDetails.getMemberId();
        Long memberId = 1L;
        List<CartDto> cartDtoList = cartRepository.selectCartListByMemberId(memberId);

        if(cartDtoList.size()>0){
            return new ResponseEntity<>(cartDtoList, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(new HttpResponseDto<>(HttpResponseInfo.NOT_FOUND.getStatusCode(), HttpResponseInfo.NOT_FOUND.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    /*
    * 장바구니에 아이템 집어 넣기
    * 파라미터 : memberId, itemId, count, price
    * 권한 : user 이상
    * */
    @PostMapping("/cart/write")
    public ResponseEntity<?> cartAddByCartInsertDto(/*@AuthenticationPrincipal PrincipalDetails principalDetails,*/
                                                       @RequestBody CartInsertDto cartInsertDto){
        //Long memberId = principalDetails.getMemberId();
        Long memberId = 1L;
        cartInsertDto.setMemberId(memberId);
        int result = cartService.addCartByCartInsertDto(cartInsertDto);
        if(result == 1){
            return new ResponseEntity<>(new HttpResponseDto<>(HttpResponseInfo.OK.getStatusCode(), HttpResponseInfo.OK.getMessage()), HttpStatus.CREATED);
        }else if(result == 2){
            return new ResponseEntity<>(new HttpResponseDto<>(HttpResponseInfo.BAD_REQUEST.getStatusCode(), HttpResponseInfo.BAD_REQUEST.getMessage(), "장바구니에 이미 있어요."), HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>(new HttpResponseDto<>(HttpResponseInfo.BAD_REQUEST.getStatusCode(), HttpResponseInfo.BAD_REQUEST.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    /*
    * 장바구니 수정
    * 파라미터 : count, memberId, itemId, cartId
    * 권한 : user
    * */
    @PutMapping("/cart/{cartId}")
    public ResponseEntity<?> cartModifyByCartId(@PathVariable("cartId") Long cartId,
                                                /*@AuthenticationPrincipal PrincipalDetails principalDetails,*/
                                                @RequestBody CartUpdateDto cartUpdateDto){
        cartUpdateDto.setCartId(cartId);
        cartUpdateDto.setMemberId(1L);
       // cartUpdateDto.setMemberId(principalDetails.getMemberId());
        int result = cartService.modifyCartByCartId(cartUpdateDto);
        if(result == 1){
            return new ResponseEntity<>(new HttpResponseDto<>(HttpResponseInfo.OK.getStatusCode(), HttpResponseInfo.OK.getMessage()), HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(new HttpResponseDto<>(HttpResponseInfo.BAD_REQUEST.getStatusCode(), HttpResponseInfo.BAD_REQUEST.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
    /*
    * 장바구니 아이템 삭제
    * 파라미터
    * */

}
