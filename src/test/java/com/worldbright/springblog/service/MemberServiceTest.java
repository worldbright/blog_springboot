package com.worldbright.springblog.service;

import com.worldbright.springblog.domain.Member;
import com.worldbright.springblog.dto.MemberDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {
    @Autowired MemberService memberService;
    @Autowired EntityManager em;

    @Test
    public void 회원가입() throws Exception {
        MemberDTO member = new MemberDTO();
        member.setName("최세현");
        member.setEmail("asdf@asdf.com");
        Long savedId = memberService.join(member);

        em.flush();
        assertEquals(member, memberService.findById(savedId));
    }
}