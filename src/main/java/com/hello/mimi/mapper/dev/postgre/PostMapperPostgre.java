package com.hello.mimi.mapper.dev.postgre;

import com.hello.mimi.mapper.PostActiveMapper;
import com.hello.mimi.standard.place.model.PlaceDTO;
import com.hello.mimi.standard.post.model.FileInfo;
import com.hello.mimi.standard.post.model.PhotoPostDTO;
import com.hello.mimi.standard.post.model.PostDTO;
import com.hello.mimi.standard.post.model.TextPostDTO;
import com.hello.mimi.util.SearchFilter;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface PostMapperPostgre extends PostActiveMapper {
    String postFilterFromToWhere =  " FROM post p join place pl on p.place_id = pl.place_id  " +
                                    "<where>" +
                                    " AND p.status = '1'" +
                                    "  <if test='title != null and title != \"\"'>" +
                                    "    AND p.title LIKE '%' || #{title} || '%'"  +
                                    "  </if>" +
                                    "  <if test='postType != null and postType != \"\"'>" +
                                    "    AND p.post_type = #{postType} " +
                                    "  </if>" +
                                    "</where>";

    @Select("<script>" +
            "SELECT p.post_id, p.title, p.post_type, p.reg_date , p.place_id, pl.place_title " +
            postFilterFromToWhere +
            "LIMIT #{cntPerPage} OFFSET #{startRowNum}" +
            "</script>")
    @Results({
            @Result(column="post_id", property="postId"),
            @Result(column="title", property="title"),
            @Result(column="post_type", property="postType"),
            @Result(column="reg_date", property="regDate"),
            @Result(column="place_id", property="placeDTO.placeId"),
            @Result(column="place_title", property="placeDTO.placeTitle")
    })
    List<PostDTO> postListByFilter(SearchFilter searchFilter);

    @Select("<script>" +
            "SELECT count(*) as totalCnt " +
            postFilterFromToWhere +
            "</script>")
    int postListByFilterCnt(SearchFilter searchFilter);

    @Select("SELECT post_id, title, body, post_type, status, place_id " +
            "FROM post WHERE post_id = #{postId}")
    @Results({
            @Result(column="post_id", property="postId"),
            @Result(column="title", property="title"),
            @Result(column="body", property="body"),
            @Result(column="post_type", property="postType"),
            @Result(column="status", property="status"),
            @Result(property="placeDTO", column="place_id", one=@One(select="readPlaceByPlaceId"))
    })
    TextPostDTO readTextPost(PostDTO postDTO);
    @Select("SELECT p.post_id, p.title, p.post_type, p.status, p.place_id " +
            "FROM post p " +
            "WHERE p.post_id = #{postId}")
    @Results({
            @Result(column="post_id", property="postId"),
            @Result(column="title", property="title"),
            @Result(column="post_type", property="postType"),
            @Result(property="fileInfos", column="post_id", many=@Many(select="selectPostPhotos")),
            @Result(property="placeDTO", column="place_id", one=@One(select="readPlaceByPlaceId"))
    })
    PhotoPostDTO readPhotoPost(PostDTO postDTO);

    @Select("SELECT place_id, place_title, road_address, mapx, mapy " +
            "FROM place " +
            "WHERE place_id = #{placeId}")
    PlaceDTO readPlaceByPlaceId(Long placeId);
    @Select("SELECT save_folder, origin_file_name, save_file FROM post_photo_info WHERE post_id = #{postId}")
    List<FileInfo> selectPostPhotos(Long postId);

    @Insert("INSERT INTO post (title, status, post_type, place_id) VALUES (#{title}, '1', #{postType}, #{placeDTO.placeId})")
    @Options(useGeneratedKeys = true, keyProperty = "postId", keyColumn = "post_id")
    int insertPost(PostDTO postDTO);

    @Insert("INSERT INTO post (title, body, status, post_type, place_id) " +
            "VALUES (#{title}, #{body}, '1', #{postType}, #{placeDTO.placeId})")
    @Options(useGeneratedKeys = true, keyProperty = "postId", keyColumn = "post_id")
    int insertTextPost(TextPostDTO postDTO);

    @Insert("<script>" +
            "INSERT INTO post_photo_info (post_id, save_folder, origin_file_name, save_file) VALUES " +
            "<foreach collection='fileInfos' item='fileinfo' separator=' , '>" +
                "(#{postId}, #{fileinfo.saveFolder}, #{fileinfo.originFileName}, #{fileinfo.saveFile})" +
		    "</foreach>" +
            "</script>")
    int insertPhotoPost(PhotoPostDTO postDTO);


    @Select("SELECT place_id, place_title, road_address, mapx, mapy " +
            "FROM place " +
            "WHERE place_title = #{placeDTO.placeTitle} " +
            "AND road_address = #{placeDTO.roadAddress} " +
            "AND mapx = #{placeDTO.mapx} " +
            "AND mapy = #{placeDTO.mapy} ")
    PlaceDTO readPlace(PostDTO postDTO);
    @Insert("INSERT INTO place (place_title, road_address, mapx, mapy) " +
            "VALUES (#{placeDTO.placeTitle}, #{placeDTO.roadAddress}, #{placeDTO.mapx}, #{placeDTO.mapy})")
    @Options(useGeneratedKeys = true, keyProperty = "placeDTO.placeId", keyColumn = "place_id")
    int insertPlace(PostDTO postDTO);

    @Update("UPDATE post SET title = #{title}, body = #{body}, post_type=#{postType} WHERE post_id = #{postId}")
    int updateTextPost(TextPostDTO postDTO);

    @Update("UPDATE post SET title = #{title}, post_type=#{postType} WHERE post_id = #{postId}")
    int updatePhotoPost(PhotoPostDTO postDTO);

    @Update("UPDATE post SET status = '9' WHERE post_id = #{postId}")
    int deleteTextPost(TextPostDTO postDTO);

    @Update("UPDATE post SET status = '9' WHERE post_id = #{postId}")
    int deletePhotoPost(PhotoPostDTO postDTO);
}
