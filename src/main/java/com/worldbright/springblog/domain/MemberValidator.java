package com.worldbright.springblog.domain;


import com.worldbright.springblog.dto.MemberDTO;
import com.worldbright.springblog.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


@Component
public class MemberValidator implements Validator {
    @Autowired
    private MemberRepository memberRepository;
    @Override
    public boolean supports(Class<?> clazz) {
        return MemberDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        MemberDTO memberDTO = (MemberDTO) target;
        if(!memberDTO.getPassword().equals(memberDTO.getPassword_confirm())) {
            errors.rejectValue("password", "key", "비밀번호 확인이 일치하지 않습니다.");
        } else if(memberRepository.findByEmail(memberDTO.getEmail()).isPresent()) {
            errors.rejectValue("email", "key", "동일한 이메일이 가입되어 있습니다.");
        }
    }
}
