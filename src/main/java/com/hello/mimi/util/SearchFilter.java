package com.hello.mimi.util;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SearchFilter {
    // 현재페이지
    private int pageIndex = 1;

    // 페이지네이션해서 제일 끝 페이지 번호
    private int pageUnit = 10;

    // 한번에 페이지 버튼을 몇개 보일 것인지.
    private int pageSize = 10;

    // 페이지 당 레코드 개수
    private int cntPerPage = 3;

    // 총 데이터 갯수
    private int totalCnt = 0;

    private int startRowNum = 0;
    private int endRowNum = 10;

    public int getStartRowNum() {
        startRowNum = (pageIndex -1) * cntPerPage;
        return startRowNum;
    }

    public int getEndRowNum() {
        endRowNum = pageIndex * cntPerPage;
        return endRowNum;
    }

    public int getPageUnit() {
        double pu = (double) totalCnt / cntPerPage;
        pageUnit = (int) Math.ceil( pu );
        return pageUnit;
    }
}