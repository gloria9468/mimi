package com.hello.mimi.standard.post;

import com.hello.mimi.standard.post.model.PhotoPostDTO;
import com.hello.mimi.standard.post.model.PostDTO;
import com.hello.mimi.standard.post.model.PostDTOFactory;
import com.hello.mimi.standard.post.model.TextPostDTO;
import com.hello.mimi.standard.post.service.PostService;
import com.hello.mimi.util.CntPerPageEnum;
import com.hello.mimi.util.PostTypeEnum;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/post")
public class PostController {
    @Autowired
    PostService postService;

    @GetMapping("/create")
    public String createPostForm(Model model) {
        PostTypeEnum[] postTypeEnum = PostTypeEnum.values();
        model.addAttribute("postTypeEnum", postTypeEnum);
        return "post/create";
    }





}
