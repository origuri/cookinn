package com.example.cookinn.model.member;

import com.example.cookinn.model.BaseDto;
import lombok.Data;

@Data
public class MemberDto extends BaseDto {

    private Long memberId;
    private String username; // 사번
    private String password;
    private String name; // 직원이름
    private String gender;
    private String tel;
    private String role;
    private String memberStatus;
}
