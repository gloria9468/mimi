package com.hello.mimi.standard.board.service.repository;

import com.hello.mimi.mapper.mybatis.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MybatisBoardRepository implements BoardDAO{
    @Autowired
    private final BoardMapper boardMapper;


}
