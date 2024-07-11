package com.hello.mimi.mapper;

import com.hello.mimi.standard.post.model.PhotoPostDTO;
import com.hello.mimi.standard.post.model.PostDTO;
import com.hello.mimi.standard.post.model.TextPostDTO;
import org.apache.ibatis.annotations.*;


@Mapper
public interface PostMapper {
    @Select("SELECT post_id, title, body FROM post WHERE post_id = #{postId}")
    PostDTO readPost(@Param("postId") Long postId);

//    @Insert("INSERT INTO post (title, body) values (#{postDTO.title}, #{postDTO.body})")
//    @Options(useGeneratedKeys = true, keyProperty = "postId", keyColumn = "post_id")
//    int createPost(@Param("postDTO") PostDTO postDTO);

    @Insert("INSERT INTO post (title, body) VALUES (#{title}, #{body})")
    @Options(useGeneratedKeys = true, keyProperty = "postId", keyColumn = "post_id")
    int insertTextPost(TextPostDTO postDTO);

    @Insert("INSERT INTO post (title, photoUrl) VALUES (#{postDTO.title}, #{postDTO.photoUrl})")
    @Options(useGeneratedKeys = true, keyProperty = "postId", keyColumn = "post_id")
    int insertPhotoPost(@Param("postDTO") PhotoPostDTO postDTO);
}
