package com.hello.mimi.standard.post.model;

import com.hello.mimi.standard.place.model.PlaceDTO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter @Setter
public class PostDTO {
    private Long postId;
    private String title;
    private String postType;
    private String status;

    @DateTimeFormat(pattern = "yyyy-mm-dd hh:mm")
    private Date regDate;
    @DateTimeFormat(pattern = "yyyy-mm-dd hh:mm")
    private Date modifyDate;

    private PlaceDTO placeDTO;


    public PostDTO() {
    }

    public PostDTO makePostDTO(String title, String postType, PlaceDTO placeDTO) {
        PostDTO pDTO = new PostDTO();
        pDTO.setTitle(title);
        pDTO.setPostType(postType);
        pDTO.setPlaceDTO(placeDTO);

        return pDTO;
    }

    @Override
    public String toString() {
        return "PostDTO{" +
                "postId=" + postId +
                ", title='" + title + '\'' +
                ", postType='" + postType + '\'' +
                '}';
    }
}
