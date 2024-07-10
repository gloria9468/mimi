package com.hello.mimi.standard.post;

import com.hello.mimi.standard.post.model.PostDTO;
import com.hello.mimi.standard.post.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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



    @GetMapping("/{postId}")
    public String readPost(@PathVariable Long postId, Model model) {
        PostDTO postDTO = postService.readPost(postId);
        model.addAttribute("postDTO", postDTO);
        return "post/detail";
    }



    //TODO :: createPost 할 때, postDTO 부모 클래스로 다르게 들어올 자식 클래스 (videoPostDTO, photoPostDTO) 에서도 같은 매서드로 작동할 수 있게.
    @PostMapping("/create")
    public String createPost(PostDTO postDTO, RedirectAttributes redirectAttributes) {
        int createCnt = postService.createPost(postDTO);

        redirectAttributes.addAttribute("postId", postDTO.getPostId());

        return "redirect:/post/{postId}";
    }


    @PutMapping("/{postId}")
    public ResponseEntity<Void> updatePost(@PathVariable Long postId, @RequestBody PostDTO postDTO) {
        postDTO.setPostId(postId);
        postService.updatePost(postDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
        return ResponseEntity.ok().build();
    }
}
