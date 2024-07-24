package com.hello.mimi.mapper;

import com.hello.mimi.standard.post.model.PostDTO;
import com.hello.mimi.util.SearchFilter;

import java.util.List;


public interface PostActiveMapper {

    int insertTextPost(PostDTO postDTO);
    int insertPhotoPost(PostDTO postDTO);


    PostDTO readTextPost(Long postId);
    PostDTO readPhotoPost(Long postId);


    int updateTextPost(PostDTO postDTO);
    int updatePhotoPost(PostDTO postDTO);


    int deleteTextPost(PostDTO postDTO);
    int deletePhotoPost(PostDTO postDTO);


    List<PostDTO> postListByFilter(SearchFilter searchFilter);

}
