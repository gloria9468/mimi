package com.hello.mimi.vo;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("FilePathMaker")
public class FilePathMaker {
    @Value("${file-store-path}")
    private String fStorePath;

    public String makeFilePath(String additionalPath) {
        System.out.println("fStorePath = " + fStorePath);
        return fStorePath + additionalPath;
    }
}
