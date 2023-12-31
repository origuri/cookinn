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

    // item에 대한 정보
    private int itemBarcode; // 아이템 바코드로 중복 체크
    private String name;
    private String productOrigin;// 원산지
    private String unit; // g, kg
    private int quantity; // 수량
    private String company; // 제조사
    private String notice; // 참고사항
    private String keep; // 보관방법
    private String itemStatus; // 구매 가능 여부
}
