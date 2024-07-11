package com.hello.mimi.standard.post.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class TextPostDTO extends PostDTO {
    private String body;

    @Override
    public String getPostType() {
        return "text";
    }

    public TextPostDTO() {

    }
}