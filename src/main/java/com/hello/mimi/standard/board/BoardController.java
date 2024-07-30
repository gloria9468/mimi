package com.hello.mimi.standard.board;


import com.hello.mimi.standard.board.service.BoardService;
import com.hello.mimi.standard.post.model.PostDTO;
import com.hello.mimi.standard.post.model.PostSearchFilter;
import com.hello.mimi.util.CntPerPageEnum;
import com.hello.mimi.util.PostTypeEnum;
import com.hello.mimi.util.SearchFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    BoardService boardService;

    @ModelAttribute("cntPerPageEnum")
    public CntPerPageEnum[] cntPerPageEnum() {
        return CntPerPageEnum.values();
    }

    @GetMapping("/list")
    public String postListByFilter(@ModelAttribute("filter") PostSearchFilter postSearchFilter, Model model) {
        PostTypeEnum[] postTypeEnum = PostTypeEnum.values();
        System.out.println(((SearchFilter) postSearchFilter).toString());
        List<PostDTO> boardList= boardService.postListByFilter(postSearchFilter);
        System.out.println(((SearchFilter) postSearchFilter).toString());
        model.addAttribute("boardList", boardList);
        model.addAttribute("filter", postSearchFilter);
        model.addAttribute("postTypeEnum", postTypeEnum);
        return "board/list";
    }

    @PostMapping("/list")
    public String postListByFilterRedirect(@ModelAttribute("filter") PostSearchFilter postSearchFilter, RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute("filter", postSearchFilter);
        return "redirect:list";
    }


}
