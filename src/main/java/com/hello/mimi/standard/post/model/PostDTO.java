package com.hello.mimi.standard.post.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PostDTO {
    private Long postId;
    private String title;
    private String postType;

    private PostDTO childPostDTO;


    public PostDTO() {
    }

    @Override
    public String toString() {
        return "PostDTO{" +
                "postId=" + postId +
                ", title='" + title + '\'' +
                ", postType='" + postType + '\'' +
                '}';
    }
}
