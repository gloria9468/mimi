package com.hello.mimi.standard.post;


import com.hello.mimi.standard.post.service.PostService;
import com.hello.mimi.util.PostTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
