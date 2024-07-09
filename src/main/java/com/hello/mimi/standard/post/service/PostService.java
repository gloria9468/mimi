package com.hello.mimi.standard.post.service;

import com.hello.mimi.standard.post.model.PostDTO;

public interface PostService {
    void createPost(PostDTO postDTO);

    PostDTO readPost(Long postId);

    void updatePost(PostDTO postDTO);

    void deletePost(Long postId);
}
