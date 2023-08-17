package com.example.cookinn.repository.member;

import com.example.cookinn.model.member.MemberDto;
import com.example.cookinn.model.member.MemberUpdateDto;

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

    /*
    * 수정, 삭제 시 db의 암호화된 비밀번호 가저옴
    * 파라미터 : memberId
    * */
    String selectDbPasswordByMemberId(Long memberId);

    /*
     * 개인정보 수정
     * 파라미터 : memberDto
     * 수정 시 비밀번호로 한번 더 체크
     * */
    int updateMemberByMemberUpdateDto(MemberUpdateDto memberUpdateDto);

    /*
     * 직원 정보 삭제 : 퇴사
     * 파라미터 : memberId
     * 권한 admin
     * 재직 중 실수로 퇴사하면 안됨 퇴사 완료 후 정보 삭제되면 안되므로
     * status만 수정하여 숨김 처리
     * */
    int updateMemberStatusByMemberId(Long memberId);
    /*
    * 로그인에 성공하면 로그인 시간을 넣어줌
    * 파라미터 : memberId
    * */
    void updateMemberLoginTimeByMemberId(Long memberId);

    /*
    * 퇴사한 직원 가져오는 로직
    * */
    List<MemberDto> selectResignMemberList();

    /*
    * 퇴사한지 3년 지난 직원에 대한 정보를 삭제함.
    * */
    void deleteMemberByMemberId(Long memberId);
}
