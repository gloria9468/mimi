package com.hello.mimi.mapper;

import com.hello.mimi.standard.post.model.PostDTO;
import org.apache.ibatis.annotations.*;


@Mapper
public interface PostMapper {
    @Select("SELECT post_id, title, body FROM post WHERE post_id = #{postId}")
    PostDTO readPost(@Param("postId") Long postId);

    @Insert("INSERT INTO post (title, body) values (#{postDTO.title}, #{postDTO.body})")
    @Options(useGeneratedKeys = true, keyProperty = "postId", keyColumn = "post_id")
    int createPost(@Param("postDTO") PostDTO postDTO);
}
