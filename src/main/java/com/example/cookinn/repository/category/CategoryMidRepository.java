package com.example.cookinn.repository.category;

import com.example.cookinn.model.category.CategoryMidDto;

import java.util.List;

public interface CategoryMidRepository {
    /*
     * 카테코리 중분류 등록
     * 파라미터 : categoryMidDto
     * 권한 : admin
     * */
    int insertCategoryMidByCategoryMidDto(CategoryMidDto categoryMidDto);

    /*
     * 카테고리 중분류 등록 시 이름 중복 확인
     * onChange 이벤트로 계속 작동
     * 파라미터 : midName
     * 권한 : admin
     * */
    CategoryMidDto selectCategoryMidByMidName(String midName);

    /*
     * 대분류에 맞는 중분류 전부 가져오기
     * 파라미터 : largeId
     * 권한 : user 이상
     * */
    List<CategoryMidDto> selectCategoryMidListByLargeId(Long largeId);
}
