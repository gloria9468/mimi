package com.hello.mimi.standard.post.service.repository;

import com.hello.mimi.mapper.PostActiveMapper;
import com.hello.mimi.standard.post.model.*;
import com.hello.mimi.util.SearchFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor // @Autowired 안 써도 됨.
public class PostRepository{
    private final PostActiveMapper postActiveMapper;
    // application.properties 에 맞춰서 알아서 Mapper로 받아서 돌음.
    // 빨간 밑줄 괜찮음. // @Qualifier 안해도 됨.

    public List<PostDTO> postListByFilter(SearchFilter searchFilter){
        return postActiveMapper.postListByFilter(searchFilter);
    }

    public PostDTO readPost(PostDTO postDTO) {
        String postType = postDTO.getPostType();
        return switch (postType){
            case "text" -> postActiveMapper.readTextPost(postDTO);
            case "photo" -> postActiveMapper.readPhotoPost(postDTO);
            default -> throw new IllegalArgumentException("exception --- from :: readRost ----");
        };
    }

    public int createPost(PostDTO postDTO) {
        if (postDTO instanceof TextPostDTO) {
            return postActiveMapper.insertTextPost((TextPostDTO) postDTO);
        } else if (postDTO instanceof PhotoPostDTO) {
            for( FileInfo fInfo : ((PhotoPostDTO) postDTO).getFileInfos() ) {
                System.out.println(fInfo.toString());
            }
            return postActiveMapper.insertPhotoPost((PhotoPostDTO) postDTO);
        }else {
            throw new IllegalArgumentException("Unknown instance --> postMapper 에 갈 수 없다.");
        }
    }

    public int updatePost(PostDTO postDTO) {
        if (postDTO instanceof TextPostDTO) {
            return postActiveMapper.updateTextPost((TextPostDTO) postDTO);
        } else if (postDTO instanceof PhotoPostDTO) {
            return postActiveMapper.updatePhotoPost((PhotoPostDTO) postDTO);
        }else {
            throw new IllegalArgumentException("Unknown instance --> postMapper 에 갈 수 없다.");
        }
    }

    public int deletePost(PostDTO postDTO) {
        if (postDTO instanceof TextPostDTO) {
            return postActiveMapper.deleteTextPost((TextPostDTO) postDTO);
        } else if (postDTO instanceof PhotoPostDTO) {
            return postActiveMapper.deletePhotoPost((PhotoPostDTO) postDTO);
        }else {
            throw new IllegalArgumentException("Unknown instance --> postMapper 에 갈 수 없다.");
        }
    }



}
