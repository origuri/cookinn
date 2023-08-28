package com.example.cookinn.model.item;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ItemUpdateDto {

    private Long itemId;
    @NotNull(message = "can not be null")
    private String name;
    @NotNull(message = "can not be null")
    private String productOrigin;// 원산지
    @NotNull(message = "can not be null")
    private String unit; // g, kg
    @NotNull(message = "can not be null")
    private int price; // 가격
    @NotNull(message = "can not be null")
    private int quantity; // 수량
    @NotNull(message = "can not be null")
    private String company; // 제조사

    private String notice; // 참고사항
    @NotNull(message = "can not be null")
    private String keep; // 보관방법
    @NotNull(message = "can not be null")
    private String itemStatus; // 구매 가능 여부

    @NotNull(message = "can not be null")
    private Long categoryLargeId;
    @NotNull(message = "can not be null")
    private Long categoryMidId;
}
