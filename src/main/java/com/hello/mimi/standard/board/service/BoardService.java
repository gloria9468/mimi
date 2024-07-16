package com.hello.mimi.standard.board.service;

import com.hello.mimi.standard.post.model.PostDTO;
import com.hello.mimi.standard.post.model.PostSearchFilter;
import com.hello.mimi.util.SearchFilter;

import java.util.List;

public interface BoardService {

    List<PostDTO> postListByFilter(SearchFilter searchFilter);
}
