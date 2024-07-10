package com.hello.mimi.standard.post.service.repository;

import com.hello.mimi.mapper.PostMapper;
import com.hello.mimi.standard.post.model.PostDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MybatisPostRepository implements PostDAO {

    @Autowired
    private final PostMapper postMapper;

    @Override
    public PostDTO readPost(Long postId) {
        return postMapper.readPost(postId);
    }

    @Override
    public int createPost(PostDTO postDTO) {
        return postMapper.createPost(postDTO);
    }


}
