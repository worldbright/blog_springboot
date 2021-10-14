package com.worldbright.springblog.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class Comment {
    @Id
    @GeneratedValue
    @Column(name = "comment_id")
    private Long id;


    private String memberName;
    private String content;

    private LocalDateTime commentDate;
    private String commentDateString;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;
}
