package com.worldbright.springblog.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PostDTO {
    private Long id;

    private LocalDateTime postDate;
    private Long memberId;

    @NotEmpty(message="제목은 필수입니다.")
    private String title;
    @NotEmpty(message="내용은 필수입니다.")
    private String content;

    private String postTags_String;
}