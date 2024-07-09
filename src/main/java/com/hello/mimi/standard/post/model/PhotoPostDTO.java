package com.hello.mimi.standard.post.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PhotoPostDTO extends PostDTO{
    private String photoId;
    private String photoPath;
    private String photoInfo;
}