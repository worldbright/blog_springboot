package com.worldbright.springblog.controller;

import com.worldbright.springblog.domain.Member;
import com.worldbright.springblog.domain.MemberRole;
import com.worldbright.springblog.domain.MemberValidator;
import com.worldbright.springblog.dto.MemberDTO;
import com.worldbright.springblog.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {
    private final MemberValidator memberValidator;
    private final MemberService memberService;

    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("memberDTO", new MemberDTO());
        return "/members/signup";
    }

    @PostMapping("/signup")
    public String signupPost(@Valid MemberDTO memberDTO, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) return "/members/signup";
        memberValidator.validate(memberDTO, bindingResult);
        if(bindingResult.hasErrors()) {
            return "/members/signup";
        } else {
            memberService.join(memberDTO);
        }
        return "redirect:/";
    }

    @GetMapping("/signin")
    public String signin() { return "/members/signin"; }

}
