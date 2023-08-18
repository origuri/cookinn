package com.example.cookinn.repository.store;

import com.example.cookinn.model.store.StoreDto;
import com.example.cookinn.model.store.StoreUpdateDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class StoreRepositoryImpl implements StoreRepository{
    private final SqlSession session;

    /*
     * store 등록
     * 파라미터 : storeDto
     * 접근권한 : admin
     * */
    @Override
    public int insertStoreByStoreDto(StoreDto storeDto) {
        int result = 0;
        try{
            result = session.insert("insertStoreByStoreDto", storeDto);
            log.info("레파지토리 insertStoreByStoreDto result -> {}", result);
        }catch (Exception e){
            log.error("레파지토리 insertStoreByStoreDto 에러 -> {}", e.getMessage());

        }
        return result;
    }

    /*
     * 전체 매장 조회
     * 파라미터 : x
     * 권한 : user 이상
     * */
    @Override
    public List<StoreDto> selectStoreList() {
        List<StoreDto> storeDtos = null;
        try{
            storeDtos = session.selectList("selectStoreList");
            log.info("레파지토리 selectStoreList size -> {}", storeDtos.size());
        }catch (Exception e){
            log.error("레파지토리 selectStoreList 에러 -> {}",e.getMessage());
        }
        return storeDtos;
    }

    /*
     * 이름으로 매장 검색 조회
     * 파라미터 : storeName
     * 권한 : user 이상
     * */
    @Override
    public List<StoreDto> selectStoreListByStoreName(String storeName) {
        List<StoreDto> storeDtos = null;
        try{
            storeDtos = session.selectList("selectStoreListByStoreName", storeName);
            log.info("레파지토리 selectStoreListByStoreName size -> {}", storeDtos.size());
        }catch (Exception e){
            log.error("레파지토리 selectStoreListByStoreName 에러 -> {}",e.getMessage());
        }
        return storeDtos;
    }

    @Override
    public StoreDto selectStoreByStoreId(Long storeId) {
        StoreDto storeDto = null;
        try{
            storeDto = session.selectOne("selectStoreByStoreId", storeId);
            log.info("레파지토리 selectStoreByStoreId storeDto -> {}",storeDto);
        }catch (Exception e){
            log.error("레파지토리 selectStoreByStoreId 에러 -> {}",e.getMessage());
        }
        return storeDto;
    }

    /*
     * 매장 정보 수정
     * 파라미터 : storeUpdateDto
     * 권한 : admin
     * */
    @Override
    public int updateStoreByStoreUpdateDto(StoreUpdateDto storeUpdateDto) {
        int result = 0;
        try{
            result = session.update("updateStoreByStoreUpdateDto", storeUpdateDto);
            log.info("레파지토리 updateStoreByStoreUpdateDto result -> {}",result);
        }catch (Exception e){
            log.error("레파지토리 updateStoreByStoreUpdateDto 에러 -> {}",e.getMessage());
        }
        return result;
    }
    /*
    * 폐점된지 1년 이상 된 매장 리스트 가져오기
    * */
    @Override
    public List<StoreDto> selectClosedStoreList() {
        List<StoreDto> storeDtos = null;
        try{
            storeDtos = session.selectList("selectClosedStoreList");
            log.info("레파지토리 selectClosedStoreList size -> {}", storeDtos.size());
        }catch (Exception e){
            log.error("레파지토리 selectClosedStoreList 에러 -> {}",e.getMessage());
        }
        return storeDtos;
    }

    /*
    * 폐점 된지 1년 이상 된 매장 삭제하기
    * */
    @Override
    public void deleteClosedStoreByStoreId(Long storeId) {
        int result = 0;
        try {
            result = session.delete("deleteClosedStoreByStoreId", storeId);
            log.info("레파지토리 deleteClosedStoreByStoreId result -> {}",result);
        }catch (Exception e){
            log.error("레파지토리 deleteClosedStoreByStoreId 에러 -> {}", e.getMessage());
        }
    }
}
