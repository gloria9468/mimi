package com.hello.mimi.standard.post.service;

import com.hello.mimi.mapper.PostActiveMapper;
import com.hello.mimi.standard.place.model.PlaceDTO;
import com.hello.mimi.standard.post.model.PhotoPostDTO;
import com.hello.mimi.standard.post.model.PostDTO;
import com.hello.mimi.standard.post.model.PostDTOFactory;
import com.hello.mimi.standard.post.model.TextPostDTO;
import com.hello.mimi.util.SearchFilter;
import com.hello.mimi.util.vo.FilePathMaker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor // @Autowired 안 써도 됨.
public class PostService{
    //@Autowired
    //PostRepository postRepository;

    private final PostActiveMapper postActiveMapper;
    // application.properties 에 맞춰서 알아서 Mapper로 받아서 돌음.
    // 빨간 밑줄 괜찮음. // @Qualifier 안해도 됨.


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
        String postType = postDTO.getPostType();
        postDTO = switch (postType){
            case "text" -> postActiveMapper.readTextPost(postDTO);
            case "photo" -> {
                yield postActiveMapper.readPhotoPost(postDTO);
            }
            default -> throw new IllegalArgumentException("exception --- from :: readRost ----");
        };
        return postDTO;
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
