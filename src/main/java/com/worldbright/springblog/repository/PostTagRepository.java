package com.worldbright.springblog.repository;

import com.worldbright.springblog.domain.PostTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostTagRepository extends JpaRepository<PostTag, Long> {
    public Optional<PostTag> findByName(String name);
}
