package com.example.cookinn.repository.store;

import com.example.cookinn.model.store.StoreDto;
import com.example.cookinn.model.store.StoreUpdateDto;

import java.util.List;

public interface StoreRepository {
    /*
     * store 등록
     * 파라미터 : storeDto
     * 접근권한 : admin
     * */
    int insertStoreByStoreDto(StoreDto storeDto);

    /*
     * 전체 매장 조회
     * 파라미터 : x
     * 권한 : user 이상
     * */
    List<StoreDto> selectStoreList();

    /*
     * 이름으로 매장 검색 조회
     * 파라미터 : storeName
     * 권한 : user 이상
     * */
    List<StoreDto> selectStoreListByStoreName(String storeName);

    /*
     * 매장 단건 조회
     * 파라미터 : storeId
     * 권한 : user 이상
     * */
    StoreDto selectStoreByStoreId(Long storeId);

    /*
     * 매장 정보 수정
     * 파라미터 : storeUpdateDto
     * 권한 : admin
     * */
    int updateStoreByStoreUpdateDto(StoreUpdateDto storeUpdateDto);

    // 페점한 매장 가져오기
    List<StoreDto> selectClosedStoreList();

    // 폐점 된지 1년 된 매장 정보 삭제하기
    void deleteClosedStoreByStoreId(Long storeId);
}
