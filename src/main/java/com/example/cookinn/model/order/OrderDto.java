package com.example.cookinn.model.order;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderDto {
    private Long orderId;
    private Long memberId;
    private String orderStatus; // 배송완료 디폴트 n
    private LocalDateTime stockedDate; // 입고예정날짜.
}
