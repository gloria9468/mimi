package com.hello.mimi.standard.post.model;

import com.hello.mimi.util.SearchFilter;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter @Setter
public class PostSearchFilter extends SearchFilter {
    private String title;
    private String postType;
    private String status;

    @DateTimeFormat(pattern = "yyyy-mm-dd hh:mm")
    private Date regDate;
    @DateTimeFormat(pattern = "yyyy-mm-dd hh:mm")
    private Date modifyDate;

}
