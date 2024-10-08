package com.hello.mimi.mapper.dev.h2;

import com.hello.mimi.mapper.PostActiveMapper;
import com.hello.mimi.standard.post.model.FileInfo;
import com.hello.mimi.standard.post.model.PhotoPostDTO;
import com.hello.mimi.standard.post.model.PostDTO;
import com.hello.mimi.standard.post.model.TextPostDTO;
import com.hello.mimi.util.SearchFilter;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface PostMapperH2 extends PostActiveMapper{
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
            "LIMIT #{startRowNum}, #{cntPerPage}" +
            "</script>")
    List<PostDTO> postListByFilter(SearchFilter searchFilter);

    @Select("SELECT post_id, title, body, post_type, status FROM post WHERE post_id = #{postId}")
    TextPostDTO readTextPost(@Param("postId") Long postId);

    @Select("SELECT p.post_id, p.title, p.post_type, 'h2test' as status " +
            "FROM post p " +
            "WHERE p.post_id = #{postId}")
    @Results({
            @Result(column="post_id", property="postId"),
            @Result(column="title", property="title"),
            @Result(column="post_type", property="postType"),
            @Result(property="fileInfos", column="post_id", many=@Many(select="selectPostPhotos"))
    })
    PhotoPostDTO readPhotoPost(@Param("postId") Long postId);

    @Select("SELECT save_folder, origin_file_name, save_file FROM post_photo_info WHERE post_id = #{postId}")
    List<FileInfo> selectPostPhotos(Long postId);


    @Insert("INSERT INTO post (title, body, status) VALUES (#{title}, #{body}, '1')")
    @Options(useGeneratedKeys = true, keyProperty = "postId", keyColumn = "post_id")
    int insertTextPost(TextPostDTO postDTO);

    @Insert("<script>" +
            "INSERT INTO post_photo_info (post_id, save_folder, origin_file_name, save_file) VALUES " +
            "<foreach collection='postDTO.fileInfos' item='fileinfo' separator=' , '>" +
                "(#{postDTO.postId}, #{fileinfo.saveFolder}, #{fileinfo.originFileName}, #{fileinfo.saveFile})" +
		    "</foreach>" +
            "</script>")
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
