package com.hello.mimi.mapper;

import com.hello.mimi.standard.post.model.PostDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface PostMapper {
    @Select("SELECT post_id, title, body FROM post WHERE post_id = #{postId}")
    PostDTO readPost(@Param("postId") Long postId);
}
