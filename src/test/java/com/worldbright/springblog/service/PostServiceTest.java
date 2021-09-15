package com.worldbright.springblog.service;

import com.worldbright.springblog.dto.MemberDTO;
import com.worldbright.springblog.dto.PostDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class PostServiceTest {
    @Autowired PostService postService;
    @Autowired MemberService memberService;

    @Test
    @Rollback(false)
    public void 게시글포스트() {
        Long memberId = memberService.join(MemberDTO.builder()
                .email("asdf")
                .name("최")
                .password("asdfasdf")
                .password_confirm("asdfasdf")
                .build()
        );
        PostDTO postDTO = PostDTO.builder()
                .title("테스트 제목")
                .content("테스트 내용")
                .postDate(LocalDateTime.now())
                .postTags_String("테스트태그1,테스트태그2,테스트태그3")
                .memberId(memberId)
                .build();

        postService.join(postDTO);
    }
}