package com.example.cookinn.model.item;

import lombok.Data;

@Data
public class ItemSearchDto {

    private Long itemId;
    private int itemBarcode; // 아이템 바코드로 중복 체크
    private String name;
    private String productOrigin;// 원산지
    private String company; // 제조사
    private String keep; // 보관방법
    private int kind; // order by 용 1은 올림차순, 2는 내림차순
}
