package com.hello.mimi.mapper;

import com.hello.mimi.standard.place.model.PlaceDTO;
import com.hello.mimi.standard.post.model.PostDTO;
import com.hello.mimi.util.SearchFilter;

import java.util.List;


public interface PostActiveMapper {
    int insertPost(PostDTO pDTO);
    int insertTextPost(PostDTO postDTO);
    int insertPhotoPost(PostDTO postDTO);
    PlaceDTO readPlace(PostDTO postDTO);
    int insertPlace(PostDTO postDTO);


    PostDTO readTextPost(PostDTO postDTO);
    PostDTO readPhotoPost(PostDTO postDTO);


    int updateTextPost(PostDTO postDTO);
    int updatePhotoPost(PostDTO postDTO);


    int deleteTextPost(PostDTO postDTO);
    int deletePhotoPost(PostDTO postDTO);


    List<PostDTO> postListByFilter(SearchFilter searchFilter);
    int postListByFilterCnt(SearchFilter searchFilter);



}
