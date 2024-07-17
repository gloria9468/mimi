package com.hello.mimi.standard.post.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class FileInfo {
    private String saveFolder;
    private String originFileName;
    private String saveFile;

    public FileInfo() {
    }
    public FileInfo(String saveFolder, String originFileName, String saveFile) {
        this.saveFolder = saveFolder;
        this.originFileName = originFileName;
        this.saveFile = saveFile;
    }

    @Override
    public String toString() {
        return "FileInfo{" +
                "saveFolder='" + saveFolder + '\'' +
                ", originFileName='" + originFileName + '\'' +
                ", saveFile='" + saveFile + '\'' +
                '}';
    }
}
