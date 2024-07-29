package com.hello.mimi.standard.post.service;

import com.hello.mimi.standard.post.model.PhotoPostDTO;
import com.hello.mimi.standard.post.model.PostDTO;
import com.hello.mimi.standard.post.service.repository.PostRepository;
import com.hello.mimi.util.bean.BeanManager;
import com.hello.mimi.util.vo.FilePathMaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService{
    @Autowired
    PostRepository postRepository;

    @Autowired
    FilePathMaker filePathMaker;


    public int createPost(PostDTO postDTO) {
        return postRepository.createPost(postDTO);
    }

    public PostDTO readPost(PostDTO postDTO) {
        String postType = postDTO.getPostType();
        postDTO = postRepository.readPost(postDTO);
        if(postDTO instanceof PhotoPostDTO){
            String fileStorePath = filePathMaker.makeFilePath("");
            ((PhotoPostDTO) postDTO).setFileStorePath(fileStorePath);
        }
        return postDTO;
    }

    public int updatePost(PostDTO postDTO) {
        return postRepository.updatePost(postDTO);
    }

    public int deletePost(PostDTO postDTO) {
        return postRepository.deletePost(postDTO);
    }


}
