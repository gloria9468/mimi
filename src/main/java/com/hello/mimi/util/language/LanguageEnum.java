package com.hello.mimi.util.language;

public enum LanguageEnum {
    KOREAN("ko", "한국어"),
    ENGLISH("en", "English");

    private final String code;
    private final String displayName;

    LanguageEnum(String code, String displayName) {
        this.code = code;
        this.displayName = displayName;
    }

    public String getCode() {
        return code;
    }

    public String getDisplayName() {
        return displayName;
    }
}