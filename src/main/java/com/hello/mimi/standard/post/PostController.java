package com.hello.mimi.standard.post;

import com.hello.mimi.standard.post.model.PostDTO;
import com.hello.mimi.standard.post.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/post")
public class PostController {
    @Autowired
    PostService postService;

    @PostMapping
    public ResponseEntity<Void> createPost(@RequestBody PostDTO postDTO) {
        postService.createPost(postDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{postId}")
    public String readPost(@PathVariable Long postId, Model model) {
        PostDTO postDTO = postService.readPost(postId);
        model.addAttribute("postDTO", postDTO);
        return "post/detail";
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
