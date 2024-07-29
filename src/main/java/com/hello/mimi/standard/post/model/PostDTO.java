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
    private String status;

    @DateTimeFormat(pattern = "yyyy-mm-dd hh:mm")
    private Date regDate;
    @DateTimeFormat(pattern = "yyyy-mm-dd hh:mm")
    private Date modifyDate;

    public PostDTO() {
    }

    public PostDTO(String title, String postType) {
        this.title = title;
        this.postType = postType;
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
