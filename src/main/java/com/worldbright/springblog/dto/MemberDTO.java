package com.worldbright.springblog.dto;

import com.worldbright.springblog.domain.Member;
import com.worldbright.springblog.domain.MemberRole;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {
    private Long id;

    @NotEmpty(message="회원 이름은 필수입니다.")
    private String name;
    @NotEmpty(message="이메일은 필수입니다.")
    private String email;
    @Size(min=7, message = "비밀번호는 8자리 이상으로 입력해주세요.")
    private String password;
    private String password_confirm;
    private MemberRole role;
}
