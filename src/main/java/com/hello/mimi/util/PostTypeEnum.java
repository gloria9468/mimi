package com.hello.mimi.util;

public enum PostTypeEnum {
    TEXT("text", "글"),
    PHOTO("photo", "사진");

    private final String postType;
    private final String displayName;

    public String getPostType() {
        return postType;
    }

    public String getDisplayName() {
        return displayName;
    }

    PostTypeEnum(String postType, String displayName) {
        this.postType = postType;
        this.displayName = displayName;
    }
}