package com.hello.mimi.util;

public enum CntPerPageEnum {
    THREE(3, "3"),
    FIVE(5, "5"),
    TEN(10, "10");


    private final int cntPerPage;
    private final String displayName;

    public int getCntPerPage() {
        return cntPerPage;
    }

    public String getDisplayName() {
        return displayName;
    }

    CntPerPageEnum(int cntPerPage, String displayName) {
        this.cntPerPage = cntPerPage;
        this.displayName = displayName;
    }
}