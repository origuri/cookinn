package com.example.cookinn.service.category;

import com.example.cookinn.model.category.CategoryMidDto;
import com.example.cookinn.repository.category.CategoryMidRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class CategoryMidService {
    private final CategoryMidRepository categoryMidRepository;

    /*
     * 카테코리 중분류 등록
     * 파라미터 : categoryMidDto
     * 권한 : admin
     * */
    public int addCategoryMidByCategoryDto(CategoryMidDto categoryMidDto) {

        if(categoryMidDto.getCategoryLargeId() == 0){
            return 2;
        }
        String toUpperCase = categoryMidDto.getMid().toUpperCase();
        categoryMidDto.setMid(toUpperCase);
        return categoryMidRepository.insertCategoryMidByCategoryMidDto(categoryMidDto);
    }

    /*
     * 카테고리 중분류 등록 시 이름 중복 확인
     * onChange 이벤트로 계속 작동
     * 파라미터 : midName
     * 권한 : admin
     * */
    public CategoryMidDto findCategoryMidByMidName(String midName) {
        String toUpperCase = midName.toUpperCase();
        return categoryMidRepository.selectCategoryMidByMidName(toUpperCase);
    }

    /*
     * 중분류 수정 -> midname만 수정 가능.
     * 파라미터 : categoryMidDto
     * 권한 : admin
     * */
    public int modifyCategoryMidByCategoryMidDto(CategoryMidDto categoryMidDto) {
        if(categoryMidDto.getMid() == null || categoryMidDto.getMid().trim().equals("")){
            return 2;
        }
        String toUpperCase = categoryMidDto.getMid().toUpperCase();
        categoryMidDto.setMid(toUpperCase);
        return categoryMidRepository.updateCategoryMidByCategoryMidDto(categoryMidDto);
    }

    /*
     * 카테고리 중분류 삭제
     * 파라미터 : largeId, midId
     * 권한 : admin
     * */
    public int removeCategoryMidBylargeIdAndMidId(Long largeId, Long midId) {
        Map<String, Long> ids = new HashMap<>();
        ids.put("largeId", largeId);
        ids.put("midId", midId);
        return categoryMidRepository.deleteCategoryMidByIds(ids);
    }
}
