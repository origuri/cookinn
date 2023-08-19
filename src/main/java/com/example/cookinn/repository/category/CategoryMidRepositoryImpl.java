package com.example.cookinn.repository.category;

import com.example.cookinn.model.category.CategoryMidDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class CategoryMidRepositoryImpl implements CategoryMidRepository{
    private final SqlSession session;

    /*
     * 카테코리 중분류 등록
     * 파라미터 : categoryMidDto
     * 권한 : admin
     * */
    @Override
    public int insertCategoryMidByCategoryMidDto(CategoryMidDto categoryMidDto) {
        int result = 0;
        try{
            result = session.insert("insertCategoryMidByCategoryMidDto", categoryMidDto);
            log.info("레파지토리 insertCategoryMidByCategoryMidDto result -> {}",result);
        }catch (Exception e){
            log.error("레파지토리 insertCategoryMidByCategoryMidDto 에러 -> {}",e.getMessage());
        }
        return result;
    }

    /*
     * 카테고리 중분류 등록 시 이름 중복 확인
     * onChange 이벤트로 계속 작동
     * 파라미터 : midName
     * 권한 : admin
     * */
    @Override
    public CategoryMidDto selectCategoryMidByMidName(String midName) {
        CategoryMidDto categoryMidDto = null;
        try{
            categoryMidDto = session.selectOne("selectCategoryMidByMidName", midName);
            log.info("레파지토리 selectCategoryMidByMidName dto -> {}", categoryMidDto);
        }catch (Exception e){
            log.error("레파지토리 selectCategoryMidByMidName 에러 -> {}", e.getMessage());
        }
        return categoryMidDto;
    }

    /*
     * 대분류에 맞는 중분류 전부 가져오기
     * 파라미터 : largeId
     * 권한 : user 이상
     * */
    @Override
    public List<CategoryMidDto> selectCategoryMidListByLargeId(Long largeId) {
        List<CategoryMidDto> categoryMidDtoList = null;
        try{
            categoryMidDtoList = session.selectList("selectCategoryMidListByLargeId", largeId);
            log.info("레파지토리 selectCategoryMidListByLargeId 사이즈 -> {}", categoryMidDtoList.size());
        }catch(Exception e){
            log.error("레파지토리 selectCategoryMidListByLargeId 에러 -> {}",e.getMessage());
        }
        return categoryMidDtoList;
    }


}
