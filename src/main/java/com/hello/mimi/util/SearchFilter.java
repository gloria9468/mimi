package com.hello.mimi.util;

import com.hello.mimi.standard.post.model.PostDTO;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SearchFilter {
    // 현재페이지
    private int pageindex = 1;

    // 페이지네이션해서 제일 끝 페이지 번호
    private int pageunit = 10;

    // 한번에 페이지 버튼을 몇개 보일 것인지.
    private int pagesize = 10;

    // 페이지 당 레코드 개수
    private int cntperpage = 3;

    // 총 데이터 갯수
    private int totalCnt = 0;

    private int startRowNum = 0;
    private int endRowNum = 10;

    public int getStartRowNum() {
        startRowNum = (pageindex-1) * cntperpage;
        return startRowNum;
    }

    public int getEndRowNum() {
        endRowNum = pageindex * cntperpage;
        return endRowNum;
    }

    public int getPageunit() {
        double pu = (double) totalCnt / cntperpage;
        pageunit = (int) Math.ceil( pu );
        return pageunit;
    }
}