package com.hello.mimi.standard.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService{
    @Autowired
    PostDAO postDAO;

    @Override
    public void createPost(PostDTO postDTO) {

    }

    @Override
    public PostDTO readPost(Long postId) {
        return postDAO.readPost(postId);
    }

    @Override
    public void updatePost(PostDTO postDTO) {

    }

    @Override
    public void deletePost(Long postId) {

    }
}
