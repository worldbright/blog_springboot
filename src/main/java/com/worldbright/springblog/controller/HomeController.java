package com.worldbright.springblog.controller;

import com.worldbright.springblog.domain.Member;
import com.worldbright.springblog.domain.Post;
import com.worldbright.springblog.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@Controller
@Slf4j
public class HomeController {
    private final PostService postService;
    @RequestMapping("/")
    public String home(Model model, @AuthenticationPrincipal Member member) {
        if(member != null)
            model.addAttribute("name", member.getName());
        model.addAttribute("posts", postService.findAll(Sort.by(Sort.Direction.DESC, "postDate")));
        return "home";
    }
}