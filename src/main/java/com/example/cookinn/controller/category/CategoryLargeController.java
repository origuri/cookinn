package com.example.cookinn.controller.category;

import com.example.cookinn.model.category.CategoryLargeDto;
import com.example.cookinn.model.httpResponse.HttpResponseDto;
import com.example.cookinn.model.httpResponse.HttpResponseInfo;
import com.example.cookinn.repository.category.CategoryLargeRepository;
import com.example.cookinn.service.category.CategoryLargeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cookinn")
@Slf4j
public class CategoryLargeController {
    private final CategoryLargeService categoryLargeService;
    private final CategoryLargeRepository categoryLargeRepository;

    /*
    * 카테고리 등록
    * 파라미터 : categoryLargeDto
    * 권한 : admin
    * */
    @PostMapping("/category/large/write")
    public ResponseEntity<?> categoryLargeAddByCategoryLargeDto(@RequestBody CategoryLargeDto categoryLargeDto){
        int result = categoryLargeService.addCategoryLargeByCategoryLargeDto(categoryLargeDto);
        if(result == 1){
            return new ResponseEntity<>(new HttpResponseDto<>(HttpResponseInfo.OK.getStatusCode(), HttpResponseInfo.OK.getMessage()), HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(new HttpResponseDto<>(HttpResponseInfo.BAD_REQUEST.getStatusCode(), HttpResponseInfo.BAD_REQUEST.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
    /*
    * 카테고리 등록 시 onChange 이벤트로 같은 이름의 카테고리 있는지 확인
    * 파라미터 : categoryLarge
    * 권한 : admin
    * */
    @GetMapping("/category/large/search/{largename}")
    public ResponseEntity<?> categoryLargeDetailByLargeName(@PathVariable("largename") String largeName){
        CategoryLargeDto categoryLargeDto = categoryLargeService.findCategoryLargeByLargeName(largeName);
        if(categoryLargeDto != null){
            // 없어야 만들어도 되는거니까 200 보냄.
            return new ResponseEntity<>(categoryLargeDto, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(new HttpResponseDto<>(HttpResponseInfo.NOT_FOUND.getStatusCode(), HttpResponseInfo.NOT_FOUND.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    /*
    * 카테고리 대분류 전부 가져오기
    * 파라미터 : x
    * 권한 : user 이상
    * */
    @GetMapping("/categories/large")
    public ResponseEntity<?> categoryLargeList(){
        List<CategoryLargeDto> categoryLargeDtoList = categoryLargeRepository.selectCategoryLargeList();
        if(!categoryLargeDtoList.isEmpty()){
            return new ResponseEntity<>(categoryLargeDtoList, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(new HttpResponseDto<>(HttpResponseInfo.BAD_REQUEST.getStatusCode(), HttpResponseInfo.BAD_REQUEST.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    /*
    * 카테고리 대분류 수정
    * 파라미터 : categoryLargeDto
    * 권한 : admin
    * */
    @PutMapping("/category/large/{largeId}")
    public ResponseEntity<?> categoryLargeModifyByCategoryLargeDto(@RequestBody CategoryLargeDto categoryLargeDto,
                                                                   @PathVariable("largeId") Long largeId){
        categoryLargeDto.setCategoryLargeId(largeId);
        int result = categoryLargeService.modifyCategoryLargeByCategoryLargeDto(categoryLargeDto);
        if(result == 1){
            return new ResponseEntity<>(new HttpResponseDto<>(HttpResponseInfo.OK.getStatusCode(), HttpResponseInfo.OK.getMessage()), HttpStatus.OK);
        }else if(result == 2){
            return new ResponseEntity<>(new HttpResponseDto<>(HttpResponseInfo.BAD_REQUEST.getStatusCode(), HttpResponseInfo.BAD_REQUEST.getMessage()), HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>(new HttpResponseDto<>(HttpResponseInfo.NOT_FOUND.getStatusCode(), HttpResponseInfo.NOT_FOUND.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    /*
    * 카테고리 대분류 삭제
    * 파라미터 : categoryLargeId
    * 권한 : admin
    * */
    @DeleteMapping("/category/large/{largeId}")
    public ResponseEntity<?> categoryLargeRemoveByLargeId(@PathVariable("largeId") Long largeId){
        int result = categoryLargeRepository.deleteCategoryLargeByLargeId(largeId);
        if(result == 1){
            return new ResponseEntity<>(new HttpResponseDto<>(HttpResponseInfo.OK.getStatusCode(), HttpResponseInfo.OK.getMessage()), HttpStatus.OK);
        }else if(result == 2){
            return new ResponseEntity<>(new HttpResponseDto<>(HttpResponseInfo.BAD_REQUEST.getStatusCode(), HttpResponseInfo.BAD_REQUEST.getMessage()), HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>(new HttpResponseDto<>(HttpResponseInfo.NOT_FOUND.getStatusCode(), HttpResponseInfo.NOT_FOUND.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

}
