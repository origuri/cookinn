package com.example.cookinn.repository.cart;

import com.example.cookinn.model.cart.CartDeleteDto;
import com.example.cookinn.model.cart.CartDto;
import com.example.cookinn.model.cart.CartInsertDto;
import com.example.cookinn.model.cart.CartUpdateDto;

import java.util.List;
import java.util.Map;

public interface CartRepository {

    /*
     * 장바구니 목록 가져오기
     * 파라미터 : x
     * 권한 : user
     * */
    List<CartDto> selectCartListByMemberId(Long memberId);

    /*
     * 장바구니에 아이템 집어 넣기
     * 파라미터 : memberId, itemId, count, price
     * 권한 : user 이상
     * */
    int insertCartByCartInsertDto(CartInsertDto cartInsertDto);

    // 장바구니에 이미 등록된 것인지 확인
    CartInsertDto selectSavedCartByMemberIdAndItemId(Map<String, Long> ids);

    /*
     * 장바구니 수정
     * 파라미터 : count, memberId, itemId, cartId
     * 권한 : user
     * */
    int updateCartByCartId(CartUpdateDto cartUpdateDto);

    /*
     * 장바구니 아이템 삭제
     * 파라미터 : cartId, memberId, itemId
     * 권한 : user
     * */
    int deleteCartByCartDeleteDto(CartDeleteDto cartDeleteDto);
}
