package com.worldbright.springblog.service;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.worldbright.springblog.domain.Comment;
import com.worldbright.springblog.domain.Post;
import com.worldbright.springblog.domain.PostTag;
import com.worldbright.springblog.domain.QPost;
import com.worldbright.springblog.dto.CommentDTO;
import com.worldbright.springblog.dto.PostDTO;
import com.worldbright.springblog.repository.MemberRepository;
import com.worldbright.springblog.repository.PostRepository;
import com.worldbright.springblog.repository.PostRepositorySupport;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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
    private final PostRepositorySupport postRepositorySupport;
    private final MemberRepository memberRepository;
    private final PostTagService postTagService;
    private final ModelMapper modelMapper;

    public Post DTO2Entity(PostDTO postDTO) {
        Post post = modelMapper.map(postDTO, Post.class);

        if(!postDTO.getPostTags_String().isBlank()) {
            List<String> postTags = Arrays.asList(postDTO.getPostTags_String().split(","));
            postTags.removeIf(s -> s.isBlank());
            List<PostTag> postTagList = postTags.stream().map(postTagService::findByNameAndCreate).collect(Collectors.toList());
            postTagList.forEach(pt -> {
                post.addPostTag(pt);
                System.out.println(pt.getName());
            });
            post.getPostTags().forEach(pt -> System.out.println(pt.getName()));
        }

        post.setMember(memberRepository.findById(postDTO.getMemberId()).get());
        post.setPostDateString(post.getPostDate().format(DateTimeFormatter.ofPattern("yy.MM.dd HH:mm")));
        return post;
    }
    public Post join(PostDTO postDTO) {
        return postRepository.save(DTO2Entity(postDTO));
    }

    public List<Post> findAll() { return postRepository.findAll(); }

    public List<Post> findAll_HomeController() {
        return postRepository.findAll_HomeController();
    }
    public Optional<Post> findById(Long id) { return postRepository.findById(id); }
    public void addComment(Post post, Comment comment) {
        post.addComment(comment);
    }

    public List<Post> findBySearchKeyword(String keyword) {
        return postRepository.findDistinctByTitleContainingOrContentContainingOrMemberNameContainingOrPostTagsNameContaining(keyword, keyword, keyword, keyword);
    }
    public List<Post> findBySearchKeywordQueryDsl(String keyword) {
        return postRepositorySupport.findBySearchKeyword(keyword);
    }
}
