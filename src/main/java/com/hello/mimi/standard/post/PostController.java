package com.hello.mimi.standard.post;

import com.hello.mimi.standard.post.model.PhotoPostDTO;
import com.hello.mimi.standard.post.model.PostDTO;
import com.hello.mimi.standard.post.model.PostDTOFactory;
import com.hello.mimi.standard.post.model.TextPostDTO;
import com.hello.mimi.standard.post.service.PostService;
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
    public String createPostForm() {
        return "post/create";
    }

    /*
    @GetMapping("/{postId}")
    public String readPost(@ModelAttribute("postType") String postType, @PathVariable Long postId, Model model) {
        PostDTO postDTO = PostDTOFactory.createPostDTO(postType);
        postDTO = postService.readPost(postType, postId);

        Object postDTOInstance = PostDTOFactory.convertPostDTO(postDTO);
        model.addAttribute("postDTO", postDTOInstance);
        return "post/detail";
    }
*/

}
