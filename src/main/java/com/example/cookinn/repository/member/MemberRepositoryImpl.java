package com.example.cookinn.repository.member;

import com.example.cookinn.model.member.MemberDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepository {
    private final SqlSession session;

    /*
     * 회원 가입
     * 파라미터 : memberDto
     * */
    @Override
    public int insertMemberByMemberDto(MemberDto memberDto) {
        int result = 0;
        try {
            result = session.insert("insertMemberByMemberDto", memberDto);
            log.info("레파지토리 insertMemberByMemberDto => {}", result);
        }catch (Exception e){
            log.info("레파지토리 insertMemberByMemberDto 에러 => {}", e.getMessage());
        }
        return result;
    }

    @Override
    public MemberDto selectMemberDtoByUsername(String username) {
        MemberDto memberDto = null;
        try{
            memberDto = session.selectOne("selectMemberDtoByUsername", username);
            log.info("레파지토리 selectMemberDtoByUsername memberDto -> {}",memberDto);
        }catch (Exception e){
            log.info("레파지토리 selectMemberDtoByUsername 에러 -> {}",e.getMessage());
        }
        return memberDto;
    }

    /*
     * 전체 직원 조회
     * 파라미터 : x
     * */
    @Override
    public List<MemberDto> selectMemberList() {
        List<MemberDto> memberDtoList = null;
        try {
            memberDtoList = session.selectList("selectMemberList");
            log.info("레파지토리 selectMemberList size -> {}",memberDtoList.size());
        }catch (Exception e){
            log.info("레파지토리 selectMemberList 에러 -> {}",e.getMessage());

        }
        return memberDtoList;
    }
    /*
     * 개인 직원 조회
     * 파라미터 : member_id(principalDetails 사용)
     * */
    @Override
    public MemberDto selectMemberByMemberId(Long memberId) {
        MemberDto memberDto = null;
        try {
            memberDto = session.selectOne("selectMemberByMemberId", memberId);
            log.info("레파지토리 selectMemberByMemberId memberDto -> {}",memberDto);
        }catch (Exception e){
            log.info("레파지토리 selectMemberByMemberId 에러 -> {}", e.getMessage());
        }
        return memberDto;
    }
}
