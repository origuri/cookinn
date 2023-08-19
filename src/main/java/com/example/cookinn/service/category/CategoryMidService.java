package com.example.cookinn.service.category;

import com.example.cookinn.model.category.CategoryMidDto;
import com.example.cookinn.repository.category.CategoryMidRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
