package com.example.cookinn.model.store;

import lombok.Data;

@Data
public class StoreUpdateDto {

    private Long storeId;
    private String storeName;
    private String storeTel;
    private String city; // 서울시
    private String street; // 영등포구
    private String zipcode; // 22번지
    private String storeStatus; // 영업 여부

}
