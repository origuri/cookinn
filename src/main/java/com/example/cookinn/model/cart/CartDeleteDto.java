package com.example.cookinn.model.cart;

import lombok.Data;

@Data
public class CartDeleteDto {
    private Long cartId;
    private Long memberId;
    private Long itemId;
}
