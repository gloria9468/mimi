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
    public PostDTO readPost(String postType, Long postId) {
        if (postType.equals("text")) {
            return postMapper.readTextPost(postId);
        } else if (postType.equals("photo")) {
            return postMapper.readPhotoPost(postId);
        }else {
            throw new IllegalArgumentException("exception --- from :: readRost ----");
        }
    }


    @Override
    public int createPost(PostDTO postDTO) {
        if (postDTO instanceof TextPostDTO) {
            return postMapper.insertTextPost((TextPostDTO) postDTO);
        } else if (postDTO instanceof PhotoPostDTO) {
            return postMapper.insertPhotoPost((PhotoPostDTO) postDTO);
        }else {
            throw new IllegalArgumentException("Unknown instance --> postMapper 에 갈 수 없다.");
        }
    }

    @Override
    public int updatePost(PostDTO postDTO) {
        if (postDTO instanceof TextPostDTO) {
            return postMapper.updateTextPost((TextPostDTO) postDTO);
        } else if (postDTO instanceof PhotoPostDTO) {
            return postMapper.updatePhotoPost((PhotoPostDTO) postDTO);
        }else {
            throw new IllegalArgumentException("Unknown instance --> postMapper 에 갈 수 없다.");
        }
    }


}
