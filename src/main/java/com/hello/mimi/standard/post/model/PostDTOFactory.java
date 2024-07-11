package com.hello.mimi.standard.post.model;


public class PostDTOFactory {

    // 팩토리 메서드
    public static PostDTO createPostDTO(String type) {
        if ("text".equals(type)) {
            return new TextPostDTO();
        } else if ("photo".equals(type)) {
            return new PhotoPostDTO();
        } else {
            throw new IllegalArgumentException("Unknown type: " + type);
        }
    }
}