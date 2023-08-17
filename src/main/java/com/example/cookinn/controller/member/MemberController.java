package com.example.cookinn.controller.member;

import com.example.cookinn.auth.PrincipalDetails;
import com.example.cookinn.model.member.MemberDto;
import com.example.cookinn.repository.member.MemberRepository;
import com.example.cookinn.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MemberController {
    private final MemberService memberService;
    private final MemberRepository memberRepository;

    /*
    * 전체 직원 조회
    * 파라미터 : x
    * */
    @GetMapping("/members")
    public ResponseEntity<?> memberList(){
        List<MemberDto> memberDtoList = memberRepository.selectMemberList();
        if(memberDtoList.size()>0){
            return new ResponseEntity<>(memberDtoList, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("현재 가입된 회원이 없습니다.", HttpStatus.NOT_FOUND);
        }
    }

    /*
    * 개인 직원 조회
    * 파라미터 : member_id(principalDetails 사용)
    * */
    @GetMapping(value = {"/user/mypage", "/user/{memberId}"})
    public ResponseEntity<?> memberDetailByMemberId(/*@AuthenticationPrincipal PrincipalDetails principalDetails*/){
        //MemberDto memberDto = memberRepository.selectMemberByMemberId(principalDetails.getMemberId());
        Long memberId = 2L;
        MemberDto memberDto = memberRepository.selectMemberByMemberId(memberId);
        if(memberDto != null){
            return new ResponseEntity<>(memberDto, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("회원 정보가 없습니다.", HttpStatus.NOT_FOUND);
        }
    }

    /*
     * 회원 가입
     * 파라미터 : memberDto
     * */
    @PostMapping("/join")
    public ResponseEntity<?> memberAddByMemberDto(@RequestBody MemberDto memberDto){
        log.info("memberDto 잘 넘어왔나? => {}",memberDto);
        int result = memberService.AddMemberByMemberDto(memberDto);
        if(result == 1){
            return new ResponseEntity<>("회원가입 성공 201", HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>("회원가입 실패 400", HttpStatus.BAD_REQUEST);
        }
    }
}
