package com.hello.mimi.standard.post.service;

import com.hello.mimi.mapper.PostActiveMapper;
import com.hello.mimi.standard.place.model.PlaceDTO;
import com.hello.mimi.standard.post.model.PhotoPostDTO;
import com.hello.mimi.standard.post.model.PostDTO;
import com.hello.mimi.standard.post.model.PostDTOFactory;
import com.hello.mimi.standard.post.model.TextPostDTO;
import com.hello.mimi.standard.post.strategy.PhotoPostStrategy;
import com.hello.mimi.standard.post.strategy.PostStrategy;
import com.hello.mimi.standard.post.strategy.TextPostStrategy;
import com.hello.mimi.util.SearchFilter;
import com.hello.mimi.util.vo.FilePathMaker;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PostService{
    @Autowired
    private PostActiveMapper postActiveMapper;
    // application.properties 에 맞춰서 알아서 Mapper로 받아서 돌음.
    // 빨간 밑줄 괜찮음. // @Qualifier 안해도 됨.
    private final Map<String, PostStrategy> strategyMap = new HashMap<>();
    public PostService(PostActiveMapper postActiveMapper) {
        strategyMap.put("text", new TextPostStrategy(postActiveMapper));
        strategyMap.put("photo", new PhotoPostStrategy(postActiveMapper));
    }




    public List<PostDTO> postListByFilter(SearchFilter searchFilter){
        return postActiveMapper.postListByFilter(searchFilter);
    }

    public void createPost(PostDTO postDTO) throws IOException {
        PlaceDTO placeDTO = postActiveMapper.readPlace(postDTO);
        if(placeDTO == null) {
            postActiveMapper.insertPlace(postDTO);
        }else{
            postDTO.setPlaceDTO(placeDTO);
        }

        System.out.println(postDTO.getPlaceDTO());
        if (postDTO instanceof PhotoPostDTO) {
            PostDTO pDTO = postDTO.makePostDTO(postDTO.getTitle(), postDTO.getPostType(), postDTO.getPlaceDTO());

            int createTextPostCnt = postActiveMapper.insertPost( pDTO );
            if(createTextPostCnt > 0) {
                postDTO.setPostId(pDTO.getPostId());
                PostDTOFactory.makePhotoDir((PhotoPostDTO) postDTO);
                int createPhotoPostCnt = postActiveMapper.insertPhotoPost(postDTO);
            }
        } else if (postDTO instanceof TextPostDTO) {
            int createCnt = postActiveMapper.insertTextPost(postDTO);
        }

    }

    public PostDTO readPost(PostDTO postDTO) {
        Class<?> clazz = postDTO.getClass();
        System.out.println("The class of postDTO is: " + clazz.getName());


        String postType = postDTO.getPostType();
        PostStrategy strategy = strategyMap.get(postType);

        if (strategy == null) {
            throw new IllegalArgumentException("전략에 없다 : " + postType);
        }
        return strategy.readPost(postDTO);
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
