package com.example.cookinn.repository.category;

import com.example.cookinn.model.category.CategoryLargeDto;

import java.util.List;

public interface CategoryLargeRepository {
    /*
     * 카테고리 등록
     * 파라미터 : categoryLargeDto
     * 권한 : admin
     * */
    int insertCategoryLargeByCategoryLargeDto(CategoryLargeDto categoryLargeDto);

    /*
     * 카테고리 등록 시 onChange 이벤트로 같은 이름의 카테고리 있는지 확인
     * 파라미터 : categoryLarge
     * 권한 : admin
     * */
    CategoryLargeDto selectCategoryLargeByLargeName(String largeToUpperCase);

    /*
     * 카테고리 대분류 전부 가져오기
     * 파라미터 : x
     * 권한 : user 이상
     * */
    List<CategoryLargeDto> selectCategoryLargeList();

    /*
     * 카테고리 대분류 수정
     * 파라미터 : categoryLargeDto
     * 권한 : admin
     * */
    int updateCategoryLargeByCategoryLargeDto(CategoryLargeDto categoryLargeDto);

    /*
     * 카테고리 대분류 삭제
     * 파라미터 : categoryLargeId
     * 권한 : admin
     * */
    int deleteCategoryLargeByLargeId(Long largeId);
}
