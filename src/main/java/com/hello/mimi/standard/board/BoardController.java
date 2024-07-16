package com.hello.mimi.standard.board;


import com.hello.mimi.standard.board.service.BoardService;
import com.hello.mimi.standard.post.model.PostDTO;
import com.hello.mimi.standard.post.model.PostSearchFilter;
import com.hello.mimi.util.SearchFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    BoardService boardService;

    @GetMapping("/list")
    public String postListByFilter(@ModelAttribute("filter") PostSearchFilter postSearchFilter, Model model) {
        List<PostDTO> boardList= boardService.postListByFilter(postSearchFilter);
        model.addAttribute("boardList", boardList);
        return "board/list";
    }


}
