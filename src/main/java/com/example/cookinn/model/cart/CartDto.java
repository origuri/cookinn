package com.example.cookinn.model.cart;

import com.example.cookinn.model.BaseDto;
import lombok.Data;

@Data
public class CartDto extends BaseDto {

    private Long cartId;
    private Long memberId;
    private Long itemId;
    private int count;
    private int cartPrice;
}
