package com.hello.mimi.standard.post;

import com.hello.mimi.mapper.mybatis.PostMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MybatisPostRepository implements PostDAO{

    @Autowired
    private final PostMapper postMapper;

    @Override
    public PostDTO readPost(Long postId) {
        return postMapper.readPost(postId);
    }
}
