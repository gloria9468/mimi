package com.hello.mimi.standard.post.service;

import com.hello.mimi.standard.post.model.PostDTO;

public interface PostService {
    int createPost(PostDTO postDTO);

    PostDTO readPost(String postType, Long postId);

    int updatePost(PostDTO postDTO);

    int deletePost(PostDTO postDTO);
}
