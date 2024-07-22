package com.hello.mimi.standard.post.model;

import com.hello.mimi.WebConfig;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.List;

@Getter @Setter
public class PhotoPostDTO extends PostDTO{

    private int postPhotoId;
    private List<MultipartFile> multipartFiles;
    private List<FileInfo> fileInfos;   // 파일 저장 위해서 기존 필드에 추가된 부분

    private String fileStorePath;

    private AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(WebConfig.class);


    public PhotoPostDTO() {
        PostDTO testPostDTO = (PostDTO) ac.getBean("getPostDtoTest");
        System.out.println(" t / f = "+testPostDTO.getTitle());
    }


    @Override
    public String getPostType() {
        return "photo";
    }

}