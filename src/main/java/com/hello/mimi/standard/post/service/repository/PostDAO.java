package com.hello.mimi.standard.post.service.repository;

import com.hello.mimi.standard.post.model.PostDTO;
import com.hello.mimi.standard.post.model.PostSearchFilter;
import com.hello.mimi.util.SearchFilter;

import java.util.List;

public interface PostDAO {
    List<PostDTO> postListByFilter(SearchFilter searchFilter);
    PostDTO readPost(String postType, Long postId);

    int createPost(PostDTO postDTO);

    int updatePost(PostDTO postDTO);

    int deletePost(PostDTO postDTO);
}
