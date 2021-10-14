package com.worldbright.springblog.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter @Setter
public class Post {
    @Id
    @Column(name = "post_id")
    @GeneratedValue
    private Long id;

    private LocalDateTime postDate;
    private String postDateString;
    private String title;
    @Column(length=1024*1024*5)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "posts", cascade = CascadeType.ALL)
    private Set<PostTag> postTags = new HashSet<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private Set<Comment> comments = new HashSet<>();

    public void addComment(Comment comment) {
        comments.add(comment);
    }
    public void addPostTag(PostTag postTag) {
        postTags.add(postTag);
        postTag.addPost(this);
    }
}
