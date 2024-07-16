package com.hello.mimi.standard.board.service;

import com.hello.mimi.standard.board.service.repository.BoardDAO;
import com.hello.mimi.standard.post.model.PostDTO;
import com.hello.mimi.standard.post.model.PostSearchFilter;
import com.hello.mimi.standard.post.service.repository.PostDAO;
import com.hello.mimi.util.SearchFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService{
    @Autowired
    BoardDAO boardDAO;

    @Autowired
    PostDAO postDAO;

    @Override
    public List<PostDTO> postListByFilter(SearchFilter searchFilter) {
        return postDAO.postListByFilter(searchFilter);
    }
}
