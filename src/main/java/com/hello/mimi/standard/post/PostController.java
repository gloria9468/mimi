package com.hello.mimi.standard.post;


import com.hello.mimi.standard.place.model.PlaceDTO;
import com.hello.mimi.standard.post.model.PostDTO;
import com.hello.mimi.standard.post.service.PostService;
import com.hello.mimi.util.PostTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/post")
public class PostController {
    @Autowired
    PostService postService;

    @GetMapping("/create")
    public String createPostForm(@ModelAttribute("placeDTO") PlaceDTO placeDTO, Model model) {
        PostDTO postDTO = new PostDTO();
        postDTO.setPlaceDTO(placeDTO);

        PostTypeEnum[] postTypeEnum = PostTypeEnum.values();
        model.addAttribute("postTypeEnum", postTypeEnum);
        model.addAttribute("postDTO", postDTO);
        return "post/create";
    }


    @PostMapping("/getCreatePost")
    public String getCreatePost (@ModelAttribute PlaceDTO placeDTO, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("placeDTO", placeDTO);
        return "redirect:create";
    }


}
