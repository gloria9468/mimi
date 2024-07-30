package com.hello.mimi.mapper.dev.postgre;

import com.hello.mimi.mapper.PostActiveMapper;
import com.hello.mimi.standard.post.model.FileInfo;
import com.hello.mimi.standard.post.model.PhotoPostDTO;
import com.hello.mimi.standard.post.model.PostDTO;
import com.hello.mimi.standard.post.model.TextPostDTO;
import com.hello.mimi.util.SearchFilter;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface PostMapperPostgre extends PostActiveMapper {
    String postFilter = "<where>" +
                        "  <if test='title != null and title != \"\"'>" +
                        "    AND p.title LIKE '%' || #{title} || '%'"  +
                        "  </if>" +
                        "  <if test='postType != null and postType != \"\"'>" +
                        "    AND p.post_type = #{postType} " +
                        "  </if>" +
                        "</where>";

    @Select("<script>" +
            "SELECT p.post_id, p.title, p.post_type, p.reg_date " +
            "FROM post p " +
            postFilter +
            "LIMIT #{startRowNum} OFFSET #{cntPerPage}" +
            "</script>")
    List<PostDTO> postListByFilter(SearchFilter searchFilter);

    @Select("<script>" +
            "SELECT count(*) as totalCnt " +
            "FROM post p " +
            postFilter +
            "</script>")
    int postListByFilterCnt(SearchFilter searchFilter);

    @Select("SELECT post_id, title, body, post_type, status FROM post WHERE post_id = #{postId}")
    TextPostDTO readTextPost(PostDTO postDTO);

    @Select("SELECT p.post_id, p.title, p.post_type, p.status " +
            "FROM post p " +
            "WHERE p.post_id = #{postId}")
    @Results({
            @Result(column="post_id", property="postId"),
            @Result(column="title", property="title"),
            @Result(column="post_type", property="postType"),
            @Result(property="fileInfos", column="post_id", many=@Many(select="selectPostPhotos"))
    })
    PhotoPostDTO readPhotoPost(PostDTO postDTO);
    @Select("SELECT save_folder, origin_file_name, save_file FROM post_photo_info WHERE post_id = #{postId}")
    List<FileInfo> selectPostPhotos(Long postId);

    @Insert("INSERT INTO post (title, status, post_type) VALUES (#{title}, '1', #{postType})")
    @Options(useGeneratedKeys = true, keyProperty = "postId", keyColumn = "post_id")
    int insertPost(PostDTO postDTO);

    @Insert("INSERT INTO post (title, body, status, post_type) VALUES (#{title}, #{body}, '1', #{postType})")
    @Options(useGeneratedKeys = true, keyProperty = "postId", keyColumn = "post_id")
    int insertTextPost(TextPostDTO postDTO);

    @Insert("<script>" +
            "INSERT INTO post_photo_info (post_id, save_folder, origin_file_name, save_file) VALUES " +
            "<foreach collection='fileInfos' item='fileinfo' separator=' , '>" +
                "(#{postId}, #{fileinfo.saveFolder}, #{fileinfo.originFileName}, #{fileinfo.saveFile})" +
		    "</foreach>" +
            "</script>")
    int insertPhotoPost(PhotoPostDTO postDTO);

    @Update("UPDATE post SET title = #{title}, body = #{body} WHERE post_id = #{postId}")
    int updateTextPost(TextPostDTO postDTO);

    @Update("UPDATE post SET title = #{title}, body = #{body} WHERE post_id = #{postId}")
    int updatePhotoPost(PhotoPostDTO postDTO);

    @Update("UPDATE post SET status = '9' WHERE post_id = #{postId}")
    int deleteTextPost(TextPostDTO postDTO);

    @Update("UPDATE post SET status = '9' WHERE post_id = #{postId}")
    int deletePhotoPost(PhotoPostDTO postDTO);
}
