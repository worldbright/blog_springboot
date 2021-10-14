package com.worldbright.springblog.repository;

import com.worldbright.springblog.domain.Post;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    @EntityGraph(attributePaths = "member")
    List<Post> findDistinctByTitleContainingOrContentContainingOrMemberNameContainingOrPostTagsNameContaining(String title, String content, String memberName, String postTagsName);

    @EntityGraph(attributePaths = "member")
    @Query("select p from Post p order by p.postDate desc")
    List<Post> findAll_HomeController();
}