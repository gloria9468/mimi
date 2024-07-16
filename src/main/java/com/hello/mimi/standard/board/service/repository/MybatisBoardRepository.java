package com.hello.mimi.standard.board.service.repository;

import com.hello.mimi.mapper.BoardMapper;
import com.hello.mimi.mapper.PostMapper;
import com.hello.mimi.standard.post.model.PostDTO;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MybatisBoardRepository implements BoardDAO{
    @Autowired
    private final BoardMapper boardMapper;


}
