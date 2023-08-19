package com.example.cookinn.service.category;

import com.example.cookinn.model.category.CategoryLargeDto;
import com.example.cookinn.repository.category.CategoryLargeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class CategoryLargeService {
    private final CategoryLargeRepository categoryLargeRepository;

    /*
     * 카테고리 등록
     * 파라미터 : categoryLargeDto
     * 권한 : admin
     * */
    public int addCategoryLargeByCategoryLargeDto(CategoryLargeDto categoryLargeDto) {
        String largeToUpperCase = categoryLargeDto.getLarge().toUpperCase();
        log.info("대문자로 잘 나오나? -> {}", largeToUpperCase);
        categoryLargeDto.setLarge(largeToUpperCase);
        int result = categoryLargeRepository.insertCategoryLargeByCategoryLargeDto(categoryLargeDto);
        return result;
    }
    /*
     * 카테고리 등록 시 onChange 이벤트로 같은 이름의 카테고리 있는지 확인
     * 파라미터 : categoryLarge
     * 권한 : admin
     * */
    public CategoryLargeDto findCategoryLargeByLargeName(String largeName) {
        String largeToUpperCase = largeName.toUpperCase();
        return categoryLargeRepository.selectCategoryLargeByLargeName(largeToUpperCase);

    }

    /*
     * 카테고리 대분류 수정
     * 파라미터 : categoryLargeDto
     * 권한 : admin
     * */
    public int modifyCategoryLargeByCategoryLargeDto(CategoryLargeDto categoryLargeDto) {
        String toUpperCase = categoryLargeDto.getLarge().toUpperCase();
        categoryLargeDto.setLarge(toUpperCase);
        int result = categoryLargeRepository.updateCategoryLargeByCategoryLargeDto(categoryLargeDto);
        return result;
    }
}
