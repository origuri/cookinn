package com.example.cookinn.auth;

import com.example.cookinn.model.member.MemberDto;
import com.example.cookinn.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

/*
*  securityConfig에서 .loginProcessingUrl("/login") 해놨기 때문에
   login 요청이 오면 자동으로 UserDetailService 타입으로 IoC 되어 있는 loadUserByUsername 함수가 실행됨.
* */
@Service
@RequiredArgsConstructor
@Slf4j
public class PrincipalDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    /*
   *  여기서 리턴한 userDetail 타입의 new PrincipalDetails(member); 는
   *  Authentication 객체안으로 들어간다.
   *  그럼 security session 안으로 들어갈 수 있다.
   * @Override
       public UserDetails loadUserDetails(T authentication) throws UsernameNotFoundException {
       return this.userDetailsService.loadUserByUsername(authentication.getName());
   }
   * */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("프린시펀 디테일 서비스 username -> {}", username);
        MemberDto member = memberRepository.selectMemberDtoByUsername(username);
        log.info("프린시펀 디테일 서비스 아이디 잘 찾아왓나? -> {}",member);
        if(member != null){
            return new PrincipalDetails(member);
        }else {
            return null;
        }
    }
}
