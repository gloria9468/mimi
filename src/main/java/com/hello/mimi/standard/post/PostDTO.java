package com.hello.mimi.standard.post;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PostDTO {
    private Long postId;
    private String title;
    private String body;

}