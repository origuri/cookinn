package com.example.cookinn.controller.category;

import com.example.cookinn.model.category.CategoryMidDto;
import com.example.cookinn.model.httpResponse.HttpResponseDto;
import com.example.cookinn.model.httpResponse.HttpResponseInfo;
import com.example.cookinn.repository.category.CategoryMidRepository;
import com.example.cookinn.service.category.CategoryMidService;
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
public class CategoryMidController {
    private final CategoryMidService categoryMidService;
    private final CategoryMidRepository categoryMidRepository;

    /*
    * 카테코리 중분류 등록
    * 파라미터 : categoryMidDto
    * 권한 : admin
    * */
    @PostMapping("/category/mid/write")
    public ResponseEntity<?> categoryMidAddByCategoryMidDto(@RequestBody CategoryMidDto categoryMidDto){
        int result = categoryMidService.addCategoryMidByCategoryDto(categoryMidDto);
        if(result == 1){
            return new ResponseEntity<>(new HttpResponseDto<>(HttpResponseInfo.OK.getStatusCode(), HttpResponseInfo.OK.getMessage()), HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(new HttpResponseDto<>(HttpResponseInfo.BAD_REQUEST.getStatusCode(), HttpResponseInfo.BAD_REQUEST.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    /*
    * 카테고리 중분류 등록 시 이름 중복 확인
    * onChange 이벤트로 계속 작동
    * 파라미터 : midName
    * 권한 : admin
    * */
    @GetMapping("/category/mid/search/{midname}")
    public ResponseEntity<?> categoryMidDetailByMidName(@PathVariable("midname") String midName){
        CategoryMidDto categoryMidDto = categoryMidService.findCategoryMidByMidName(midName);
        if(categoryMidDto != null){
            // 있다는 뜻
            return new ResponseEntity<>(categoryMidDto, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(new HttpResponseDto<>(HttpResponseInfo.NOT_FOUND.getStatusCode(), HttpResponseInfo.NOT_FOUND.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    /*
    * 대분류에 맞는 중분류 전부 가져오기
    * 파라미터 : largeId
    * 권한 : user 이상
    * */
    @GetMapping("/categories/large/{largeId}/mid")
    public ResponseEntity<?> categoryMidListByLargeId(@PathVariable("largeId") Long largeId){
        List<CategoryMidDto> categoryMidDtoList = categoryMidRepository.selectCategoryMidListByLargeId(largeId);
        if(!categoryMidDtoList.isEmpty()){
            return new ResponseEntity<>(categoryMidDtoList, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(new HttpResponseDto<>(HttpResponseInfo.NOT_FOUND.getStatusCode(), HttpResponseInfo.NOT_FOUND.getMessage()), HttpStatus.NOT_FOUND);
        }
    }
}
