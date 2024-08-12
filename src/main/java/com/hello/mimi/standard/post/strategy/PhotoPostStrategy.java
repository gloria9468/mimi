package com.hello.mimi.standard.post.strategy;

import com.hello.mimi.mapper.PostActiveMapper;
import com.hello.mimi.standard.post.model.PostDTO;

public class PhotoPostStrategy implements PostStrategy{
    private final PostActiveMapper postActiveMapper;

    public PhotoPostStrategy(PostActiveMapper postActiveMapper) {
        this.postActiveMapper = postActiveMapper;
    }

    @Override
    public PostDTO readPost(PostDTO postDTO){
        return postActiveMapper.readPhotoPost(postDTO);
    }
}
