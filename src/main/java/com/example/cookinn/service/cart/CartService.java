package com.example.cookinn.service.cart;

import com.example.cookinn.model.cart.CartInsertDto;
import com.example.cookinn.repository.cart.CartRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@Slf4j
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;

    /*
     * 장바구니에 아이템 집어 넣기
     * 파라미터 : memberId, itemId, count, price
     * 권한 : user 이상
     * */
    public int addCartByCartInsertDto(CartInsertDto cartInsertDto) {
        // 1000원 짜리 2개 사면 2000원이 되야 함.
        int totalPrice = cartInsertDto.getPrice() * cartInsertDto.getCount();
        cartInsertDto.setCartPrice(totalPrice);
        int result = cartRepository.insertCartByCartInsertDto(cartInsertDto);
        return result;
    }
}
