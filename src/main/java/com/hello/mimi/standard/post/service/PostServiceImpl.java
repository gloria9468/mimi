package com.hello.mimi.standard.post.service;

import com.hello.mimi.standard.post.service.repository.PostDAO;
import com.hello.mimi.standard.post.model.PostDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService{
    @Autowired
    PostDAO postDAO;

    @Override
    public int createPost(PostDTO postDTO) {
        return postDAO.createPost(postDTO);
    }

    @Override
    public PostDTO readPost(String postType, Long postId) {
        return postDAO.readPost(postType, postId);
    }

    @Override
    public int updatePost(PostDTO postDTO) {
        return postDAO.updatePost(postDTO);
    }

    @Override
    public void deletePost(Long postId) {

    }
}
