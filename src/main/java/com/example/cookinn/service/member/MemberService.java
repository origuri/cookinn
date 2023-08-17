package com.example.cookinn.service.member;

import com.example.cookinn.model.member.MemberDto;
import com.example.cookinn.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class MemberService {
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    /*
     * 회원 가입
     * 파라미터 : memberDto
     * */
    public int AddMemberByMemberDto(MemberDto memberDto) {
        int result = 0;
        String rawPassword = memberDto.getPassword();
        String hashPassword = passwordEncoder.encode(rawPassword);
        memberDto.setPassword(hashPassword);
        result = memberRepository.insertMemberByMemberDto(memberDto);
        return result;
    }
}
