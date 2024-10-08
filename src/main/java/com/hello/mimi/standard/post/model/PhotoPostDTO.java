package com.hello.mimi.standard.post.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;



@Getter @Setter
public class PhotoPostDTO extends PostDTO{

    private int postPhotoId;
    private List<MultipartFile> multipartFiles;
    private List<FileInfo> fileInfos;   // 파일 저장 위해서 기존 필드에 추가된 부분

    private String fileStorePath;


    public PhotoPostDTO() {

    }

    @Override
    public String getPostType() {
        return "photo";
    }

    @Override
    public String toString() {
        return "PhotoPostDTO{" +
                "postPhotoId=" + postPhotoId +
                ", multipartFiles=" + multipartFiles +
                ", fileInfos=" + fileInfos +
                ", fileStorePath='" + fileStorePath + '\'' +
                '}';
    }
}