package com.hello.mimi.standard.post.service.repository;

import com.hello.mimi.mapper.PostMapper;
import com.hello.mimi.standard.post.model.PhotoPostDTO;
import com.hello.mimi.standard.post.model.PostDTO;
import com.hello.mimi.standard.post.model.TextPostDTO;
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

//    @Override
//    public int createPost(PostDTO postDTO) {
//        return postMapper.createPost(postDTO);
//    }

    @Override
    public int createPost(PostDTO postDTO) {
        // Handle the creation logic based on the type of PostDTO

        if (postDTO instanceof TextPostDTO) {
            System.out.println("11");
            return postMapper.insertTextPost((TextPostDTO) postDTO);
        } else if (postDTO instanceof PhotoPostDTO) {
            System.out.println("22");
            return postMapper.insertPhotoPost((PhotoPostDTO) postDTO);
        }
        System.out.println("33");
        return 0;
    }


}
