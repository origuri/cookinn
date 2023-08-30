package com.example.cookinn.model.cart;

import lombok.Data;

@Data
public class CartUpdateDto {

    private Long cartId;
    private Long memberId;
    private Long itemId;
    private int count;
    private int cartPrice;

    // 아이템 가격
    private int price; // 가격
}
