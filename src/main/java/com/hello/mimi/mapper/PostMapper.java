package com.hello.mimi.mapper;

import com.hello.mimi.standard.post.model.PhotoPostDTO;
import com.hello.mimi.standard.post.model.PostDTO;
import com.hello.mimi.standard.post.model.PostSearchFilter;
import com.hello.mimi.standard.post.model.TextPostDTO;
import com.hello.mimi.util.SearchFilter;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface PostMapper {
    @Select("<script>" +
            "SELECT p.post_id, p.title, p.post_type, p.reg_date " +
            "FROM post p " +
            "<where>" +
            "  <if test='title != null and title != \"\"'>" +
            "    AND p.title LIKE CONCAT('%', #{title}, '%')" +
            "  </if>" +
            "  <if test='title != null and title != \"\"'>" +
            "    AND p.title LIKE CONCAT('%', #{title}, '%')" +
            "  </if>" +
            "</where>" +
            "</script>")
    List<PostDTO> postListByFilter(SearchFilter searchFilter);

    @Select("SELECT post_id, title, body, post_type, status FROM post WHERE post_id = #{postId}")
    TextPostDTO readTextPost(@Param("postId") Long postId);

    @Select("SELECT p.post_id, p.title, p.post_type, ph.photo_url " +
            "FROM post p, post_photo_info ph " +
            "WHERE p.post_id = ph.post_id " +
            "and p.post_id = #{postId}")
    PhotoPostDTO readPhotoPost(@Param("postId") Long postId);

    @Insert("INSERT INTO post (title, body, status) VALUES (#{title}, #{body}, '1')")
    @Options(useGeneratedKeys = true, keyProperty = "postId", keyColumn = "post_id")
    int insertTextPost(TextPostDTO postDTO);

    @Insert("INSERT INTO post (title, photoUrl) VALUES (#{postDTO.title}, #{postDTO.photoUrl})")
    @Options(useGeneratedKeys = true, keyProperty = "postId", keyColumn = "post_id")
    int insertPhotoPost(@Param("postDTO") PhotoPostDTO postDTO);

    @Update("UPDATE post SET title = #{title}, body = #{body} WHERE post_id = #{postId}")
    int updateTextPost(TextPostDTO postDTO);

    @Update("UPDATE post SET title = #{title}, body = #{body} WHERE post_id = #{postId}")
    int updatePhotoPost(PhotoPostDTO postDTO);

    @Update("UPDATE post SET status = '9' WHERE post_id = #{postId}")
    int deleteTextPost(TextPostDTO postDTO);

    @Update("UPDATE post SET status = '9' WHERE post_id = #{postId}")
    int deletePhotoPost(PhotoPostDTO postDTO);
}
