package com.example.cookinn.repository.category;

import com.example.cookinn.model.category.CategoryLargeDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class CategoryLargeRepositoryImpl implements CategoryLargeRepository{
    private final SqlSession session;

    /*
     * 카테고리 등록
     * 파라미터 : categoryLargeDto
     * 권한 : admin
     * */
    @Override
    public int insertCategoryLargeByCategoryLargeDto(CategoryLargeDto categoryLargeDto) {
        int result = 0;
        try{
            result = session.insert("insertCategoryLargeByCategoryLargeDto", categoryLargeDto);
            log.info("레파지토리 insertCategoryLargeByCategoryLargeDto result -> {}",result);
        }catch (Exception e){
            log.error("레파지토리 insertCategoryLargeByCategoryLargeDto 에러 -> {}",e.getMessage());
        }
        return result;
    }

    /*
     * 카테고리 등록 시 onChange 이벤트로 같은 이름의 카테고리 있는지 확인
     * 파라미터 : categoryLarge
     * 권한 : admin
     * */
    @Override
    public CategoryLargeDto selectCategoryLargeByLargeName(String largeToUpperCase) {
        CategoryLargeDto categoryLargeDto = null;
        try{
            categoryLargeDto = session.selectOne("selectCategoryLargeByLargeName" ,largeToUpperCase);
            log.info("레파지토리 selectCategoryLargeByLargeName categoryDto -> {}",categoryLargeDto);
        }catch (Exception e){
            log.error("레파지토리 selectCategoryLargeByLargeName 에러 -> {}",e.getMessage());

        }
        return categoryLargeDto;
    }

    /*
     * 카테고리 대분류 전부 가져오기
     * 파라미터 : x
     * 권한 : user 이상
     * */
    @Override
    public List<CategoryLargeDto> selectCategoryLargeList() {
        List<CategoryLargeDto> categoryLargeDtoList = null;
        try{
            categoryLargeDtoList = session.selectList("selectCategoryLargeList");
            log.info("레파지토리 selectCategoryLargeList 사이즈 -> {}",categoryLargeDtoList.size());
        }catch (Exception e){
            log.error("레파지토리 selectCategoryLargeList 에러 -> {}",e.getMessage());
        }
        return categoryLargeDtoList;
    }

    /*
     * 카테고리 대분류 수정
     * 파라미터 : categoryLargeDto
     * 권한 : admin
     * */
    @Override
    public int updateCategoryLargeByCategoryLargeDto(CategoryLargeDto categoryLargeDto) {
        int result = 0;
        try{
            result = session.update("updateCategoryLargeByCategoryLargeDto", categoryLargeDto);
            log.info("레파지토리 updateCategoryLargeByCategoryLargeDto result -> {}", result);
        }catch (Exception e){
            log.error("레파지토리 updateCategoryLargeByCategoryLargeDto 에러 -> {}", e.getMessage());
        }
        return result;
    }

    /*
     * 카테고리 대분류 삭제
     * 파라미터 : categoryLargeId
     * 권한 : admin
     * */
    @Override
    public int deleteCategoryLargeByLargeId(Long largeId) {
        int result = 0;
        try{
            result = session.delete("deleteCategoryLargeByLargeId", largeId);
            log.info("레파지토리 deleteCategoryLargeByLargeId result -> {}", result);
        }catch (Exception e){
            log.error("레파지토리 deleteCategoryLargeByLargeId 에러 -> {}", e.getMessage());
        }
        return result;
    }
}
