package com.hello.mimi.util;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

@Getter @Setter
public class SearchFilter {
    // 현재페이지
    private int pageIndex = 1;

    // 페이지네이션해서 시작-끝 페이지 번호
    // 1 페이지 ~ 10 페이지
    private int pageUnitStart = 1;
    private int pageUnitEnd = 10;
    private int pageUnitTotal = 10; // 총 몇 페이지까지 있느냐

    // 한번에 페이지 버튼을 몇개 보일 것인지.
    private int pageSize = 10;
    private int midPageIndex = 0;

    // 페이지 당 레코드 개수
    private int cntPerPage = 10;

    // 총 데이터 갯수
    private int totalCnt = 0;

    private int startRowNum = 0;
    private int endRowNum = 10;


    public int getStartRowNum() {
        this.startRowNum = (pageIndex -1) * cntPerPage;
        return startRowNum;
    }

    public int getEndRowNum() {
        this.endRowNum = pageIndex * cntPerPage;
        return endRowNum;
    }

    public int getMidPageIndex() {
        calcMidPageIndex();
        return midPageIndex;
    }

    public void setTotalCnt(int totalCnt) {
        this.totalCnt = totalCnt;
        calcPageFilter();
    }

    private void calcMidPageIndex() {
        double mid = (double) pageSize / 2;
        this.midPageIndex = (int) Math.floor( mid );
    }

    private void calcPageFilter() {
        // reset :: pageUnitTotal
        double pu = (double) totalCnt / cntPerPage;
        int ceilTotal = (int) Math.ceil( pu );
        this.pageUnitTotal = ceilTotal == 0 ? 1 : ceilTotal;

        // reset :: midPageIndex
        calcMidPageIndex();


        if(pageIndex <= midPageIndex){
            this.pageUnitStart = 1;
            this.pageUnitEnd = Math.min(pageUnitTotal, pageSize); // pageUnitTotal < pageSize ? pageUnitTotal : pageSize;
        } else {
            if ( ((pageUnitTotal - midPageIndex) > 0) && (pageIndex > (pageUnitTotal - midPageIndex)) ) {
                this.pageUnitStart = Math.max(1, pageUnitTotal - pageSize + 1);
                this.pageUnitEnd = pageUnitTotal;
            }else {
                this.pageUnitStart = pageIndex - midPageIndex;
                this.pageUnitEnd = Math.min(pageUnitTotal, pageIndex + midPageIndex - 1);
            }
        }
    }

    @Override
    public String toString() {
        return "SearchFilter{" +
                "pageIndex=" + pageIndex +
                ", pageUnitStart=" + pageUnitStart +
                ", pageUnitEnd=" + pageUnitEnd +
                ", pageUnitTotal=" + pageUnitTotal +
                ", pageSize=" + pageSize +
                ", midPageIndex=" + midPageIndex +
                ", cntPerPage=" + cntPerPage +
                ", totalCnt=" + totalCnt +
                ", startRowNum=" + startRowNum +
                ", endRowNum=" + endRowNum +
                '}';
    }
}