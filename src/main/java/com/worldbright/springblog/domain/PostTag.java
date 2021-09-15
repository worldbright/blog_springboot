package com.worldbright.springblog.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class PostTag {
    @Id
    @GeneratedValue
    @Column(name = "posttag_id")
    private Long id;

    @NotEmpty
    private String name;

    @ManyToMany
    @JoinTable(name = "post_posttag",
                joinColumns = @JoinColumn(name = "posttag_id"),
                inverseJoinColumns = @JoinColumn(name = "post_id"))
    private List<Post> posts = new ArrayList<>();

    public PostTag(String name) {
        this.name = name;
    }

    public void addPost(Post post) {
        posts.add(post);
    }
}
