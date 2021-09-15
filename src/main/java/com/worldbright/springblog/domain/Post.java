package com.worldbright.springblog.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @ManyToOne(fetch = FetchType.EAGER)
    private Member member;

    @ManyToMany(mappedBy = "posts")
    private List<PostTag> postTags = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();
}
