package com.hello.mimi.standard.post;

import com.hello.mimi.standard.post.model.*;
import com.hello.mimi.standard.post.service.PostService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.w3c.dom.ls.LSOutput;

import java.beans.PropertyEditorSupport;

import static java.awt.SystemColor.text;

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



    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        System.out.println("init--------");
        binder.registerCustomEditor(PostDTO.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String type) throws IllegalArgumentException {
                System.out.println("set--------");
                PostDTO postDTO = PostDTOFactory.createPostDTO(type);
                setValue(postDTO);
            }
        });
    }



    @PostMapping("/create")
    public String createPost(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        String postType = request.getParameter("postType");

        PostDTO postDTO;
        if ("text".equals(postType)) {
            postDTO = new TextPostDTO();
            postDTO.setTitle(request.getParameter("title"));
            ((TextPostDTO) postDTO).setBody(request.getParameter("body"));
        } else if ("photo".equals(postType)) {
            postDTO = new PhotoPostDTO();
            postDTO.setTitle(request.getParameter("title"));
            ((PhotoPostDTO) postDTO).setPhotoUrl(request.getParameter("photoUrl"));
        } else {
            throw new IllegalArgumentException("Unknown type: " + postType);
        }

        if (postDTO instanceof TextPostDTO) {
            System.out.println("TextPostDTO instance detected");
        } else if (postDTO instanceof PhotoPostDTO) {
            System.out.println("PhotoPostDTO instance detected");
        } else {
            System.out.println("Unknown PostDTO instance");
        }

        int createCnt = postService.createPost(postDTO);
        redirectAttributes.addAttribute("postId", postDTO.getPostId()); // 예시로 postId 값 설정

        return "redirect:post/{postId}";
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
