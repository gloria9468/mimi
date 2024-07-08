package com.hello.mimi.mapper.mybatis;

import com.hello.mimi.standard.post.PostDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


public interface PostMapper {
    PostDTO readPost(@Param("postId") Long postId);
}
