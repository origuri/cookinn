package com.example.cookinn.model.store;

import com.example.cookinn.model.BaseDto;
import lombok.Data;

@Data
public class StoreDto extends BaseDto {

    private Long   storeId;
    private String storeNum; // 사업자 등록번호
    private String storeName;
    private String storeTel; // 업장 번호
    private String city; // 서울시
    private String street; // 영등포구
    private String zipcode; // 22번지
    private String storeStatus; // 영업 여부
    private String adminName; // 누가 등록했는가.
}
