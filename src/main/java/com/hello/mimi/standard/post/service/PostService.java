package com.hello.mimi.standard.post.service;

import com.hello.mimi.standard.post.model.PostDTO;
import com.hello.mimi.standard.post.service.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService{
    @Autowired
    PostRepository postRepository;

    public int createPost(PostDTO postDTO) {
        return postRepository.createPost(postDTO);
    }

    public PostDTO readPost(String postType, Long postId) {


        return postRepository.readPost(postType, postId);
    }

    public int updatePost(PostDTO postDTO) {
        return postRepository.updatePost(postDTO);
    }

    public int deletePost(PostDTO postDTO) {
        return postRepository.deletePost(postDTO);
    }
}
