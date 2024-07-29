package com.hello.mimi.standard.post.model;


import com.hello.mimi.util.bean.BeanManager;
import com.hello.mimi.util.vo.FilePathMaker;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

// 팩토리 메서드
@Component
public class PostDTOFactory {
    @Autowired
    FilePathMaker filePathMaker;

    public static String fileStorePath;

    public PostDTOFactory(FilePathMaker filePathMaker) {
        this.filePathMaker = filePathMaker;
        fileStorePath = filePathMaker.makeFilePath("");
    }


    public static PostDTO createPostDTO(String type) {
        return switch (type) {
            case "text" -> new TextPostDTO();
            case "photo" -> {
                PhotoPostDTO photoPostDTO = new PhotoPostDTO();
                yield photoPostDTO;
            }
            default -> throw new IllegalArgumentException("Unknown type: " + type);
        };
    }

    public static PhotoPostDTO makePhotoDir(PhotoPostDTO photoPostDTO) throws IOException {
        List<MultipartFile> files = photoPostDTO.getMultipartFiles();
        if (files == null || files.isEmpty()) {
            throw new IllegalArgumentException("No files to upload");
        }


        String realPath = fileStorePath;
        String today = new SimpleDateFormat("yyMMdd").format(new Date());
        File folder = new File(realPath, today);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        List<FileInfo> fileInfos = new ArrayList<>();
        for (MultipartFile mfile : files) {
            if (mfile.isEmpty()) {
                continue; // Skip empty files
            }

            String originalFileName = mfile.getOriginalFilename();
            if (originalFileName == null || originalFileName.isEmpty()) {
                continue; // Skip files with no name
            }

            FileInfo fileInfo = new FileInfo();
            String saveFileName = UUID.randomUUID().toString()
                    + originalFileName.substring(originalFileName.lastIndexOf('.'));
            fileInfo.setSaveFolder(today);
            fileInfo.setOriginFileName(originalFileName);
            fileInfo.setSaveFile(saveFileName);

            try {
                mfile.transferTo(new File(folder, saveFileName));
            } catch (IOException e) {
                // Handle the error appropriately, maybe log it or rethrow it
                throw new IOException("Failed to save file " + originalFileName, e);
            }

            fileInfos.add(fileInfo);
        }

        photoPostDTO.setFileInfos(fileInfos);
        return photoPostDTO;
    }


    public static Object convertPostDTO(PostDTO postDTO) {
        if (postDTO instanceof TextPostDTO) {
            TextPostDTO textPostDTO = (TextPostDTO) postDTO;
            return textPostDTO;
        } else if (postDTO instanceof PhotoPostDTO) {
            PhotoPostDTO photoPostDTO = (PhotoPostDTO) postDTO;
            return photoPostDTO;
        } else {
            throw new IllegalArgumentException("cannot convert PostDTO Instance");
        }
    }


}

