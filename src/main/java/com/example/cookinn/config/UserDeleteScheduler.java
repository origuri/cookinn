package com.example.cookinn.config;

import com.example.cookinn.model.member.MemberDto;
import com.example.cookinn.model.store.StoreDto;
import com.example.cookinn.repository.member.MemberRepository;
import com.example.cookinn.repository.store.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserDeleteScheduler {
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;

    /*
    * 매일 자정에 실행 됨.
    * memberStatus = 'N'이고 로그인 안한지 1년 이상되면 삭제되게
    *                   초 분 시
    * @Scheduled(cron = "0 0 0 * * *") // 매일 자정에 실행
    * */
    //
    @Scheduled(cron = "0 0 0 * * *")
    public void deleteResignUser() {

        List<MemberDto> memberDtoList = memberRepository.selectResignMemberList();
        if(!memberDtoList.isEmpty()){
            for(MemberDto memberDto : memberDtoList){
                memberRepository.deleteMemberByMemberId(memberDto.getMemberId());
            }
        }
    }
    @Scheduled(cron = "0 0 0 * * *")
    public void deleteClosedStore(){
        List<StoreDto> storeDtoList = storeRepository.selectClosedStoreList();
        if(!storeDtoList.isEmpty()){
            for(StoreDto storeDto : storeDtoList){
                storeRepository.deleteClosedStoreByStoreId(storeDto.getStoreId());
            }
        }
    }

}
