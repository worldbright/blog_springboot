package com.worldbright.springblog.service;

import com.worldbright.springblog.domain.Comment;
import com.worldbright.springblog.domain.Member;
import com.worldbright.springblog.domain.Post;
import com.worldbright.springblog.dto.CommentDTO;
import com.worldbright.springblog.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final ModelMapper modelMapper;
    private final MemberService memberService;
    private final PostService postService;
    public Comment DTO2Entity(CommentDTO commentDTO) {
        Comment comment = modelMapper.map(commentDTO, Comment.class);

        Member member = memberService.findById(commentDTO.getMemberId())
                .orElseThrow(()->new IllegalStateException("comment 등록 : memberId가 잘못되었습니다."));
        comment.setMember(member);

        Post post = postService.findById(commentDTO.getPostId())
                .orElseThrow(()->new IllegalStateException("comment 등록 : postId가 잘못되었습니다."));
        comment.setPost(post);

        comment.setCommentDateString(comment.getCommentDate().format(DateTimeFormatter.ofPattern("yy.MM.dd HH:mm")));
        return comment;
    }

    public Comment join(CommentDTO commentDTO) {
        Comment comment = DTO2Entity(commentDTO);
        postService.addComment(comment.getPost(), comment);
        return commentRepository.save(comment);
    }
}
