package com.worldbright.springblog.controller;

import com.worldbright.springblog.domain.Member;
import com.worldbright.springblog.domain.Post;
import com.worldbright.springblog.dto.PostDTO;
import com.worldbright.springblog.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@Slf4j
public class PostController {
    private final PostService postService;

    @GetMapping("/post")
    public String post(Model model) {
        model.addAttribute("postDTO", new PostDTO());
        return "/posts/post";
    }

    @PostMapping("/post")
    public String post(@Valid PostDTO postDTO, BindingResult bindingResult, @AuthenticationPrincipal Member member) {
        if(bindingResult.hasErrors()) return "/posts/post";
        if(member != null) postDTO.setMemberId(member.getId());
        else               postDTO.setMemberId(1L);
        postDTO.setPostDate(LocalDateTime.now());
        log.info(postDTO.toString());
        postService.join(postDTO);
        return "redirect:/";
    }

    @GetMapping("/posts/{postId}")
    public String showPost(@PathVariable("postId") Long postId, Model model) {
        Optional<Post> postOptional = postService.findById(postId);
        if(postOptional.isPresent()) {
            model.addAttribute("post", postOptional.get());
            return "posts/showPost";
        } else {
            return "redirect:/";
        }
    }
}
