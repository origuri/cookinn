package com.example.cookinn.service.cart;

import com.example.cookinn.model.cart.CartDto;
import com.example.cookinn.model.cart.CartInsertDto;
import com.example.cookinn.model.cart.CartUpdateDto;
import com.example.cookinn.repository.cart.CartRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Transactional
@Service
@Slf4j
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;

    private int getTotalPrice(int price, int count){
        return price * count;
    }

    /*
     * 장바구니에 아이템 집어 넣기
     * 파라미터 : memberId, itemId, count, price
     * 권한 : user 이상
     * */
    public int addCartByCartInsertDto(CartInsertDto cartInsertDto) {

        // 이미 장바구니에 있는 지 확인
        Map<String, Long> ids = new HashMap<>();
        ids.put("memberId", cartInsertDto.getMemberId());
        ids.put("itemId", cartInsertDto.getItemId());
        CartInsertDto savedCartInsertDto = cartRepository.selectSavedCartByMemberIdAndItemId(ids);
        if(savedCartInsertDto != null){
            return 2;
        }else{
            // 1000원 짜리 2개 사면 2000원이 되야 함.
            cartInsertDto.setCartPrice(getTotalPrice(cartInsertDto.getPrice(),cartInsertDto.getCount()));
            int result = cartRepository.insertCartByCartInsertDto(cartInsertDto);
            return result;
        }


    }

    /*
     * 장바구니 수정
     * 파라미터 : count, memberId, itemId, cartId
     * 권한 : user
     * */
    public int modifyCartByCartId(CartUpdateDto cartUpdateDto) {
        // 개수를 수정하면 값도 달라져야 함.
        cartUpdateDto.setCartPrice(getTotalPrice(cartUpdateDto.getPrice(),cartUpdateDto.getCount()));
        int result = cartRepository.updateCartByCartId(cartUpdateDto);

        return result;
    }
}
