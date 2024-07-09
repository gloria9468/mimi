package com.hello.mimi.standard.post.service.repository;

import com.hello.mimi.standard.post.model.PostDTO;

public interface PostDAO {
    PostDTO readPost(Long postId);
}
