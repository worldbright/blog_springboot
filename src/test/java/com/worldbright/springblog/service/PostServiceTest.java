package com.worldbright.springblog.service;

import com.worldbright.springblog.domain.Post;
import com.worldbright.springblog.dto.MemberDTO;
import com.worldbright.springblog.dto.PostDTO;
import com.worldbright.springblog.repository.MemberRepository;
import com.worldbright.springblog.repository.PostRepository;
import com.worldbright.springblog.repository.PostRepositorySupport;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Slf4j
class PostServiceTest {
    @Autowired PostService postService;
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;
    @Autowired PostRepository postRepository;
    @Autowired PostRepositorySupport postRepositorySupport;

    @Test
    public void 검색() {
        List<Post> searched = postRepository.findDistinctByTitleContainingOrContentContainingOrMemberNameContainingOrPostTagsNameContaining("태그", "태그", "태그", "태그");
        searched.forEach(e -> System.out.println(e.getMember().getName()));

        List<Post> searched2 = postRepositorySupport.findBySearchKeyword("2");
        searched2.forEach(e -> System.out.println(e.getMember().getName()));


    }
}