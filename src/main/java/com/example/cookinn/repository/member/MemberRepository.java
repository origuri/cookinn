package com.example.cookinn.repository.member;

import com.example.cookinn.model.member.MemberDto;

import java.util.List;

public interface MemberRepository {
    /*
     * 회원 가입
     * 파라미터 : memberDto
     * */
    int insertMemberByMemberDto(MemberDto memberDto);

    /*
    * security용
    * */
    MemberDto selectMemberDtoByUsername(String username);
    /*
     * 전체 직원 조회
     * 파라미터 : x
     * */
    List<MemberDto> selectMemberList();

    /*
     * 개인 직원 조회
     * 파라미터 : member_id(principalDetails 사용)
     * */
    MemberDto selectMemberByMemberId(Long memberId);
}
