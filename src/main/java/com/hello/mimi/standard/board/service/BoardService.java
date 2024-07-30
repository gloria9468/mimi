package com.hello.mimi.standard.board.service;

import com.hello.mimi.mapper.PostActiveMapper;
import com.hello.mimi.standard.post.model.PostDTO;
import com.hello.mimi.util.SearchFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor // @Autowired 안 써도 됨.
public class BoardService{
    private final PostActiveMapper postActiveMapper;
    // application.properties 에 맞춰서 알아서 Mapper로 받아서 돌음.
    // 빨간 밑줄 괜찮음. // @Qualifier 안해도 됨.

    public List<PostDTO> postListByFilter(SearchFilter searchFilter) {
        int totalCnt = postActiveMapper.postListByFilterCnt(searchFilter);
        searchFilter.setTotalCnt(totalCnt);
        return postActiveMapper.postListByFilter(searchFilter);
    }
}
