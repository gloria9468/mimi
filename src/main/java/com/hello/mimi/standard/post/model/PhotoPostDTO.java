package com.hello.mimi.standard.post.model;

import com.hello.mimi.WebConfig;
import com.hello.mimi.vo.FilePathMaker;
import jakarta.annotation.PostConstruct;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;



@Getter @Setter
public class PhotoPostDTO extends PostDTO{

    private int postPhotoId;
    private List<MultipartFile> multipartFiles;
    private List<FileInfo> fileInfos;   // 파일 저장 위해서 기존 필드에 추가된 부분

    private String fileStorePath;

    public PhotoPostDTO() {
        System.out.println("PhotoPostDTO() ---..--- fileStorePath = " + fileStorePath);
    }

    @Override
    public String getPostType() {
        return "photo";
    }

    public String getFileStorePath(String additionalPath) {
        return null;
    }
}