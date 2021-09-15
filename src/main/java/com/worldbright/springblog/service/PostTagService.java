package com.worldbright.springblog.service;

import com.worldbright.springblog.domain.Post;
import com.worldbright.springblog.domain.PostTag;
import com.worldbright.springblog.repository.PostTagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class PostTagService {
    private final PostTagRepository postTagRepository;

    public PostTag join(String name) {
        PostTag postTag = new PostTag(name);
        return postTagRepository.save(postTag);
    }

    public Optional<PostTag> findByName(String name) {
        return postTagRepository.findByName(name);
    }

    public PostTag findByNameAndCreate(String name) {
        return postTagRepository.findByName(name).orElseGet(() -> join(name));
    }
}