package com.hello.mimi.standard.post.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PhotoPostDTO extends PostDTO{
    private String photoUrl;

    @Override
    public String getPostType() {
        return "photo";
    }

    public PhotoPostDTO() {
    }
}