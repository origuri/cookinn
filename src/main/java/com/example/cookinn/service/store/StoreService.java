package com.example.cookinn.service.store;

import com.example.cookinn.model.store.StoreDto;
import com.example.cookinn.model.store.StoreUpdateDto;
import com.example.cookinn.repository.store.StoreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
@Slf4j
public class StoreService {

    private final StoreRepository storeRepository;

    /*
     * 매장 정보 수정
     * 파라미터 : storeUpdateDto
     * 권한 : admin
     * */
    public int modifyStoreByStoreUpdateDto(StoreUpdateDto storeUpdateDto) {
        int result = storeRepository.updateStoreByStoreUpdateDto(storeUpdateDto);
        if(result == 1){
            return result;
        }else{
            return 2;
        }
    }
}
