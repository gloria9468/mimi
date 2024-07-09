package com.hello.mimi.standard.post.service.repository;

import com.hello.mimi.mapper.PostMapper;
import com.hello.mimi.standard.post.model.PostDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

//@Repository
@RequiredArgsConstructor
public class H2PostRepository implements PostDAO {

    @Autowired
    private final PostMapper postMapper;

    @Override
    public PostDTO readPost(Long postId) {
        return postMapper.readPost(postId);
    }
}
