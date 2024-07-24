package com.hello.mimi.util.vo;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@NoArgsConstructor
public class FilePathMaker {
    private String fStorePath;

    public FilePathMaker(String fStorePath) {
        this.fStorePath = fStorePath;
    }

    public String makeFilePath(String additionalPath) {
        System.out.println("fStorePath = " + fStorePath);
        return fStorePath + additionalPath;
    }
}
