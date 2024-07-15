package com.hello.mimi.mapper;

import com.hello.mimi.standard.post.model.PhotoPostDTO;
import com.hello.mimi.standard.post.model.PostDTO;
import com.hello.mimi.standard.post.model.TextPostDTO;
import org.apache.ibatis.annotations.*;


@Mapper
public interface PostMapper {
    @Select("SELECT post_id, title, body, post_type FROM post WHERE post_id = #{postId}")
    TextPostDTO readTextPost(@Param("postId") Long postId);

    @Select("SELECT p.post_id, p.title, p.post_type, ph.photo_url " +
            "FROM post p, post_photo_info ph " +
            "WHERE p.post_id = ph.post_id " +
            "and p.post_id = #{postId}")
    PhotoPostDTO readPhotoPost(@Param("postId") Long postId);

    @Insert("INSERT INTO post (title, body) VALUES (#{title}, #{body})")
    @Options(useGeneratedKeys = true, keyProperty = "postId", keyColumn = "post_id")
    int insertTextPost(TextPostDTO postDTO);

    @Insert("INSERT INTO post (title, photoUrl) VALUES (#{postDTO.title}, #{postDTO.photoUrl})")
    @Options(useGeneratedKeys = true, keyProperty = "postId", keyColumn = "post_id")
    int insertPhotoPost(@Param("postDTO") PhotoPostDTO postDTO);

    @Update("UPDATE post SET title = #{title}, body = #{body} WHERE post_id = #{postId}")
    int updateTextPost(TextPostDTO postDTO);

    @Update("UPDATE post SET title = #{postDTO.title}, body = #{postDTO.body} WHERE post_id = #{postDTO.postId}")
    int updatePhotoPost(PhotoPostDTO postDTO);
}
