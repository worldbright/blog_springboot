package com.worldbright.springblog.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter @Setter
@NoArgsConstructor
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
    private Set<Post> posts = new HashSet<>();

    public PostTag(String name) {
        this.name = name;
    }

    public void addPost(Post post) {
        posts.add(post);
    }
}
