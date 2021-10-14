package com.worldbright.springblog.service;

import com.worldbright.springblog.domain.Member;
import com.worldbright.springblog.domain.MemberRole;
import com.worldbright.springblog.dto.MemberDTO;
import com.worldbright.springblog.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MemberService implements UserDetailsService {
    private final MemberRepository memberRepository;
    private final ModelMapper modelMapper;

    public MemberService(MemberRepository memberRepository, ModelMapper modelMapper) {
        this.memberRepository = memberRepository;
        this.modelMapper = modelMapper;

        if(!memberRepository.findById(1L).isPresent()) {
            join(MemberDTO.builder()
                    .email("guest")
                    .name("익명")
                    .password("00000000")
                    .password_confirm("00000000")
                    .build());
            join(MemberDTO.builder()
                    .email("guest2")
                    .name("익명2")
                    .password("00000000")
                    .password_confirm("00000000")
                    .build());
            join(MemberDTO.builder()
                    .email("guest3")
                    .name("익명3")
                    .password("00000000")
                    .password_confirm("00000000")
                    .build());
        }
    }

    @Transactional
    public Long join(MemberDTO memberDTO) {
        Member member = modelMapper.map(memberDTO, Member.class);
        validateDuplicateUser(member);

        member.setRole(MemberRole.MEMBER);
        member.setPassword(new BCryptPasswordEncoder().encode(member.getPassword()));

        memberRepository.save(member);
        return member.getId();
    }
    private void validateDuplicateUser(Member member) {
        Optional<Member> findMember = memberRepository.findByEmail(member.getEmail());
        if(findMember.isPresent()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    public List<Member> findUsers() { return memberRepository.findAll(); }
    public Optional<Member> findById(Long userId) { return memberRepository.findById(userId); }

    @Override
    public Member loadUserByUsername(String username) throws UsernameNotFoundException {
        return memberRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
