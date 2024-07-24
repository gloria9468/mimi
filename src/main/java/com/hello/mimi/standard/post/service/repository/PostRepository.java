package com.hello.mimi.standard.post.service.repository;

import com.hello.mimi.mapper.h2.PostMapper;
import com.hello.mimi.standard.post.model.*;
import com.hello.mimi.util.SearchFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostRepository {
    @Autowired
    private final PostMapper postMapper;

    public List<PostDTO> postListByFilter(SearchFilter searchFilter){
        return postMapper.postListByFilter(searchFilter);
    }

    public PostDTO readPost(String postType, Long postId) {
        return switch (postType){
            case "text" -> postMapper.readTextPost(postId);
            case "photo" -> {
                yield  postMapper.readPhotoPost(postId);
            }
            default -> throw new IllegalArgumentException("exception --- from :: readRost ----");
        };
    }


    public int createPost(PostDTO postDTO) {
        if (postDTO instanceof TextPostDTO) {
            return postMapper.insertTextPost((TextPostDTO) postDTO);
        } else if (postDTO instanceof PhotoPostDTO) {
            for( FileInfo fInfo : ((PhotoPostDTO) postDTO).getFileInfos() ) {
                System.out.println(fInfo.toString());
            }
            return postMapper.insertPhotoPost((PhotoPostDTO) postDTO);
        }else {
            throw new IllegalArgumentException("Unknown instance --> postMapper 에 갈 수 없다.");
        }
    }

    public int updatePost(PostDTO postDTO) {
        if (postDTO instanceof TextPostDTO) {
            return postMapper.updateTextPost((TextPostDTO) postDTO);
        } else if (postDTO instanceof PhotoPostDTO) {
            return postMapper.updatePhotoPost((PhotoPostDTO) postDTO);
        }else {
            throw new IllegalArgumentException("Unknown instance --> postMapper 에 갈 수 없다.");
        }
    }

    public int deletePost(PostDTO postDTO) {
        if (postDTO instanceof TextPostDTO) {
            return postMapper.deleteTextPost((TextPostDTO) postDTO);
        } else if (postDTO instanceof PhotoPostDTO) {
            return postMapper.deletePhotoPost((PhotoPostDTO) postDTO);
        }else {
            throw new IllegalArgumentException("Unknown instance --> postMapper 에 갈 수 없다.");
        }
    }
}
