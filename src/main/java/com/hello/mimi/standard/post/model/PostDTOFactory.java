package com.hello.mimi.standard.post.model;


import jakarta.servlet.http.HttpServletRequest;

public class PostDTOFactory {
    public static PostDTO createPostDTO(String type) {
        if ("text".equals(type)) {
            return new TextPostDTO();
        } else if ("photo".equals(type)) {
            return new PhotoPostDTO();
        } else {
            //throw new IllegalArgumentException("Unknown type: " + type);
            return null;
        }
    }


    // 팩토리 메서드
    public static PostDTO createPostDTO(String postType, HttpServletRequest request) {
        PostDTO postDTO;
        switch (postType) {
            case "text":
                postDTO = new TextPostDTO();
                postDTO.setTitle(request.getParameter("title"));
                ((TextPostDTO) postDTO).setBody(request.getParameter("body"));
                break;
            case "photo":
                postDTO = new PhotoPostDTO();
                postDTO.setTitle(request.getParameter("title"));
                ((PhotoPostDTO) postDTO).setPhotoUrl(request.getParameter("photoUrl"));
                break;
            default:
                throw new IllegalArgumentException("Unknown type: " + postType);
        }
        return postDTO;
    }
}

