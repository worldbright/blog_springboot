package com.worldbright.springblog.service;

import com.worldbright.springblog.domain.Post;
import com.worldbright.springblog.domain.PostTag;
import com.worldbright.springblog.dto.PostDTO;
import com.worldbright.springblog.repository.MemberRepository;
import com.worldbright.springblog.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final PostTagService postTagService;
    private final ModelMapper modelMapper;
    public Post join(PostDTO postDTO) {
        Post post = modelMapper.map(postDTO, Post.class);

        if(!postDTO.getPostTags_String().isBlank()) {
            List<String> postTags = Arrays.asList(postDTO.getPostTags_String().split(","));
            postTags.removeIf(s -> s.isBlank());
            List<PostTag> postTagList = postTags.stream().map(postTagService::findByNameAndCreate).collect(Collectors.toList());
            postTagList.forEach(pt -> pt.addPost(post));
            post.setPostTags(postTagList);
        }

        post.setMember(memberRepository.findById(postDTO.getMemberId()).get());
        post.setPostDateString(post.getPostDate().format(DateTimeFormatter.ofPattern("yy.MM.dd HH:mm")));

        return postRepository.save(post);
    }

    public List<Post> findAll() { return postRepository.findAll(); }
    public List<Post> findAll(Sort sort) {
        return postRepository.findAll(sort);
    }
    public Optional<Post> findById(Long id) { return postRepository.findById(id); }
}
