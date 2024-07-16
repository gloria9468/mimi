package com.hello.mimi.mapper;

import com.hello.mimi.standard.post.model.PhotoPostDTO;
import com.hello.mimi.standard.post.model.PostDTO;
import com.hello.mimi.standard.post.model.TextPostDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface BoardMapper {
    @Select("SELECT p.post_id, p.title, p.post_type, p.reg_date FROM post p")
    List<PostDTO> readBoard();

}
