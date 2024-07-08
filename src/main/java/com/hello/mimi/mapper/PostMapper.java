package com.hello.mimi.mapper;

import com.hello.mimi.standard.post.PostDTO;
import org.apache.ibatis.annotations.Param;


public interface PostMapper {
    PostDTO readPost(@Param("postId") Long postId);
}
