package com.example.cookinn.repository.cart;

import com.example.cookinn.model.cart.CartDto;
import com.example.cookinn.model.cart.CartInsertDto;
import com.example.cookinn.model.cart.CartUpdateDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
@Slf4j
public class CartRepositoryImpl implements CartRepository{

    private final SqlSession session;

    /*
     * 장바구니 목록 가져오기
     * 파라미터 : x
     * 권한 : user
     * */
    @Override
    public List<CartDto> selectCartListByMemberId(Long memberId) {
        log.info("memberId -> {}", memberId);
        List<CartDto> cartDtoList = null;
        try{
            cartDtoList = session.selectList("selectCartListByMemberId", memberId);
            log.info("레파지토리 selectCartList size -> {}",cartDtoList.size());
        }catch (Exception e){
            log.error("레파지토리 selectCartList 에러 -> {}",e.getMessage());

        }
        return cartDtoList;
    }

    /*
     * 장바구니에 아이템 집어 넣기
     * 파라미터 : memberId, itemId, count, price
     * 권한 : user 이상
     * */
    @Override
    public int insertCartByCartInsertDto(CartInsertDto cartInsertDto) {
        log.info("cartInsertDto -> {}", cartInsertDto);
        int result = 0;
        try{
            result = session.insert("insertCartByCartInsertDto", cartInsertDto);
            log.info("레파지토리 insertCartByCartInsertDto result -> {}", result);
        }catch (Exception e){
            log.error("레파지토리 insertCartByCartInsertDto 에러 -> {}", e.getMessage());

        }
        return result;
    }

    // 장바구니에 이미 등록된 것인지 확인.
    @Override
    public CartInsertDto selectSavedCartByMemberIdAndItemId(Map<String, Long> ids) {
        CartInsertDto cartInsertDto = null;
        try{
            cartInsertDto = session.selectOne("selectSavedCartByMemberIdAndItemId", ids);
            log.info("레파지토리 selectSavedCartByMemberIdAndItemId cartInsertDto -> {}", cartInsertDto);
        }catch (Exception e){
            log.error("레파지토리 selectSavedCartByMemberIdAndItemId 에러 -> {}", e.getMessage());

        }
        return cartInsertDto;
    }

    /*
     * 장바구니 수정
     * 파라미터 : count, memberId, itemId, cartId
     * 권한 : user
     * */
    @Override
    public int updateCartByCartId(CartUpdateDto cartUpdateDto) {
        int result = 0;
        try{
            result = session.update("updateCartByCartId", cartUpdateDto);
            log.info("레파지토리 updateCartByCartId result -> {}", result);
        }catch (Exception e){
            log.error("레파지토리 updateCartByCartId 에러 -> {}", e.getMessage());
        }
        return result;
    }
}
