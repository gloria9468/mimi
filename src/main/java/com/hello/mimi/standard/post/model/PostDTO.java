package com.hello.mimi.standard.post.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter @Setter
public class PostDTO {
    private Long postId;
    private String title;
    private String postType;

    @DateTimeFormat(pattern = "yyyy-mm-dd hh:mm")
    private Date regDate;
    @DateTimeFormat(pattern = "yyyy-mm-dd hh:mm")
    private Date modifyDate;

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
