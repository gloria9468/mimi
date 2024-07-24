package com.hello.mimi.standard.board.service;

import com.hello.mimi.standard.post.model.PostDTO;
import com.hello.mimi.standard.post.service.repository.PostRepository;
import com.hello.mimi.util.SearchFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService{
    @Autowired
    PostRepository postRepository;

    public List<PostDTO> postListByFilter(SearchFilter searchFilter) {
        return postRepository.postListByFilter(searchFilter);
    }
}
