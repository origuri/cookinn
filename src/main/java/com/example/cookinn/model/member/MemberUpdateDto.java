package com.example.cookinn.model.member;

import com.example.cookinn.model.BaseDto;
import lombok.Data;

@Data
public class MemberUpdateDto extends BaseDto {
    private Long memberId;
    private String password;
    private String newPassword;
    private String tel;
    private Long storeId; // 부서가 바뀔 수 있음.
    private String memberStatus;
}
