package com.worldbright.springblog.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor
public class CommentDTO {
    private String memberName;
    private String content;
    private LocalDateTime commentDate;

    private Long memberId;
    private Long postId;
}
