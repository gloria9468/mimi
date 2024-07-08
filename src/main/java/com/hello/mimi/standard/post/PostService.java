package com.hello.mimi.standard.post;

import org.springframework.stereotype.Service;

public interface PostService {
    void createPost(PostDTO postDTO);

    PostDTO readPost(Long postId);

    void updatePost(PostDTO postDTO);

    void deletePost(Long postId);
}
