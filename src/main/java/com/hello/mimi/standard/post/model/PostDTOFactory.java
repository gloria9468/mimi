package com.hello.mimi.standard.post.model;

// 팩토리 메서드
public class PostDTOFactory {
    public static PostDTO createPostDTO(String type) {
        if ("text".equals(type)) {
            return new TextPostDTO();
        } else if ("photo".equals(type)) {
            return new PhotoPostDTO();
        } else {
            throw new IllegalArgumentException("Unknown type: " + type);

        }
    }

    public static Object convertPostDTO(PostDTO postDTO) {
        if (postDTO instanceof TextPostDTO) {
            TextPostDTO textPostDTO = (TextPostDTO) postDTO;
            return textPostDTO;
        } else if (postDTO instanceof PhotoPostDTO) {
            PhotoPostDTO photoPostDTO = (PhotoPostDTO) postDTO;
            return photoPostDTO;
        }else {
            throw new IllegalArgumentException("cannot convert PostDTO Instance");
        }
    }



}

