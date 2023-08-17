package com.example.cookinn.controller.member;

import com.example.cookinn.auth.PrincipalDetails;
import com.example.cookinn.model.httpResponse.HttpResponseDto;
import com.example.cookinn.model.httpResponse.HttpResponseInfo;
import com.example.cookinn.model.member.MemberDto;
import com.example.cookinn.model.member.MemberUpdateDto;
import com.example.cookinn.repository.member.MemberRepository;
import com.example.cookinn.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MemberController {
    private final MemberService memberService;
    private final MemberRepository memberRepository;

    /*
     * 회원 가입
     * 파라미터 : memberDto
     * */
    @PostMapping("/join")
    public ResponseEntity<?> memberAddByMemberDto(@RequestBody MemberDto memberDto){
        log.info("memberDto 잘 넘어왔나? => {}",memberDto);
        int result = memberService.AddMemberByMemberDto(memberDto);
        if(result == 1){
            return new ResponseEntity<>(new HttpResponseDto<>(HttpResponseInfo.OK.getStatusCode(), HttpResponseInfo.OK.getMessage()), HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(new HttpResponseDto<>(HttpResponseInfo.BAD_REQUEST.getStatusCode(), HttpResponseInfo.BAD_REQUEST.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    /*
    * 전체 직원 조회
    * 파라미터 : x
    * */
    @GetMapping("/cookinn/members")
    public ResponseEntity<?> memberList(){
        List<MemberDto> memberDtoList = memberRepository.selectMemberList();
        if(memberDtoList.size()>0){
            return new ResponseEntity<>(memberDtoList, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(new HttpResponseDto<>(HttpResponseInfo.NOT_FOUND.getStatusCode(), HttpResponseInfo.NOT_FOUND.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    /*
    * 개인 직원 조회
    * 파라미터 : member_id(principalDetails 사용)
    * */
    @GetMapping(value = {"/cookinn/user/mypage", "/cookinn/user/{memberId}"})
    public ResponseEntity<?> memberDetailByMemberId(/*@AuthenticationPrincipal PrincipalDetails principalDetails*/){
        //MemberDto memberDto = memberRepository.selectMemberByMemberId(principalDetails.getMemberId());
        Long memberId = 2L;
        MemberDto memberDto = memberRepository.selectMemberByMemberId(memberId);
        if(memberDto != null){
            return new ResponseEntity<>(memberDto, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(new HttpResponseDto<>(HttpResponseInfo.NOT_FOUND.getStatusCode(), HttpResponseInfo.NOT_FOUND.getMessage()), HttpStatus.NOT_FOUND);
        }
    }


    /*
    * 개인정보 수정
    * 파라미터 : memberDto
    * 수정 시 비밀번호로 한번 더 체크
    * */
    @PutMapping("/cookinn/user/{memberId}")
    public ResponseEntity<?> memberModifyByMemberUpdateDto(@PathVariable("memberId") Long memberId,
                                                           @RequestBody MemberUpdateDto memberUpdateDto){
        memberUpdateDto.setMemberId(memberId);
        int result = memberService.modifyMemberByMemberUpdateDto(memberUpdateDto);
        if(result == 1){
            return new ResponseEntity<>(new HttpResponseDto<>(HttpResponseInfo.OK.getStatusCode(), HttpResponseInfo.OK.getMessage()), HttpStatus.OK);
        }else if(result == 2){
            return new ResponseEntity<>(new HttpResponseDto<>(HttpResponseInfo.BAD_REQUEST.getStatusCode(), HttpResponseInfo.BAD_REQUEST.getMessage()), HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>(new HttpResponseDto<>(HttpResponseInfo.NOT_FOUND.getStatusCode(), HttpResponseInfo.NOT_FOUND.getMessage()), HttpStatus.NOT_FOUND);
        }
    }
    /*
    * 직원 정보 삭제 : 퇴사
    * 파라미터 : memberId
    * 권한 admin
    * 재직 중 실수로 퇴사하면 안됨 퇴사 완료 후 정보 삭제되면 안되므로
    * status만 수정하여 숨김 처리
    * */
    //@Secured("ADMIN")
    @PutMapping("/cookinn/user/resign/{memberId}")
    public ResponseEntity<?> memberStatusModifyByMemberId(@PathVariable("memberId") Long memberId){
        int result = memberRepository.updateMemberStatusByMemberId(memberId);
        if(result == 1){
            return new ResponseEntity<>(new HttpResponseDto<>(HttpResponseInfo.OK.getStatusCode(), HttpResponseInfo.OK.getMessage()), HttpStatus.OK);
        }else if(result == 2){
            return new ResponseEntity<>(new HttpResponseDto<>(HttpResponseInfo.BAD_REQUEST.getStatusCode(), HttpResponseInfo.BAD_REQUEST.getMessage()), HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>(new HttpResponseDto<>(HttpResponseInfo.NOT_FOUND.getStatusCode(), HttpResponseInfo.NOT_FOUND.getMessage()), HttpStatus.NOT_FOUND);
        }
    }
}
