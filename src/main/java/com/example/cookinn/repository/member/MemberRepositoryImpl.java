package com.example.cookinn.repository.member;

import com.example.cookinn.model.member.MemberDto;
import com.example.cookinn.model.member.MemberUpdateDto;
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
            log.error("레파지토리 insertMemberByMemberDto 에러 => {}", e.getMessage());
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
            log.error("레파지토리 selectMemberDtoByUsername 에러 -> {}",e.getMessage());
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
            log.error("레파지토리 selectMemberList 에러 -> {}",e.getMessage());

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
            log.error("레파지토리 selectMemberByMemberId 에러 -> {}", e.getMessage());
        }
        return memberDto;
    }

    /*
     * 수정, 삭제 시 db의 암호화된 비밀번호 가저옴
     * 파라미터 : memberId
     * */
    @Override
    public String selectDbPasswordByMemberId(Long memberId) {
        String dbPassword = null;
        try{
            dbPassword = session.selectOne("selectDbPasswordByMemberId", memberId);
            log.info("레파지토리 selectDbPasswordByMemberId -> {}", dbPassword);
        }catch (Exception e){
            log.error("레파지토리 selectDbPasswordByMemberId 에러 -> {}", e.getMessage());
        }
        return dbPassword;
    }

    /*
     * 개인정보 수정
     * 파라미터 : memberDto
     * 수정 시 비밀번호로 한번 더 체크
     * */
    @Override
    public int updateMemberByMemberUpdateDto(MemberUpdateDto memberUpdateDto) {
        int result = 0;
        try {
            result = session.update("updateMemberByMemberUpdateDto", memberUpdateDto);
            log.info("레파지토리 updateMemberByMemberUpdateDto result -> {}",result);
        }catch (Exception e){
            log.error("레파지토리 updateMemberByMemberUpdateDto 에러 -> {}",e.getMessage());
        }
        return result;
    }

    /*
     * 직원 정보 삭제 : 퇴사
     * 파라미터 : memberId
     * 권한 admin
     * 재직 중 실수로 퇴사하면 안됨 퇴사 완료 후 정보 삭제되면 안되므로
     * status만 수정하여 숨김 처리
     * */
    @Override
    public int updateMemberStatusByMemberId(Long memberId) {
        int result = 0;
        try{
            result = session.update("updateMemberStatusByMemberId", memberId);
            log.info("레파지토리 updateMemberStatusByMemberId result -> {}", result);
        }catch (Exception e){
            log.error("레파지토리 updateMemberStatusByMemberId 에러 -> {}", e.getMessage());
        }
        return result;
    }

    /*
     * 로그인에 성공하면 로그인 시간을 넣어줌
     * 파라미터 : memberId
     * */
    @Override
    public void updateMemberLoginTimeByMemberId(Long memberId) {
        int result = 0;
        try{
           result = session.update("updateMemberLoginTimeByMemberId", memberId);
           log.info("레파지토리 updateMemberLoginTimeByMemberId result -> {}",result);
        }catch (Exception e){
           log.error("레파지토리 updateMemberLoginTimeByMemberId 에러 -> {}",e.getMessage());

        }
    }

    /*
     * 퇴사한 직원 가져오는 로직
     * */
    @Override
    public List<MemberDto> selectResignMemberList() {
        List<MemberDto> memberDtoList = null;
        try{
            memberDtoList = session.selectList("selectResignMemberList");
            log.info("레파지토리 selectResignMemberList size -> {}", memberDtoList.size());
        }catch (Exception e){
            log.error("레파지토리 selectResignMemberList 에러 -> {}", e.getMessage());

        }
        return memberDtoList;
    }

    /*
     * 퇴사한지 3년 지난 직원에 대한 정보를 삭제함.
     * */
    @Override
    public void deleteMemberByMemberId(Long memberId) {
        int result = 0;
        try {
            result = session.delete("deleteMemberByMemberId", memberId);
            log.info("레파지토리 deleteMemberByMemberId result -> {}",result);
        }catch (Exception e){
            log.error("레파지토리 deleteMemberByMemberId 에러 -> {}", e.getMessage());
        }
    }
}
