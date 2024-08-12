package com.hello.mimi.standard.post.strategy;

import com.hello.mimi.standard.post.model.PostDTO;

public interface PostStrategy {
    PostDTO readPost(PostDTO postDTO);

}
