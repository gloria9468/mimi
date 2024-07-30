package com.hello.mimi.standard.post;

import com.hello.mimi.standard.post.model.*;
import com.hello.mimi.standard.post.service.PostService;
import com.hello.mimi.util.bean.BeanManager;
import com.hello.mimi.util.PostTypeEnum;
import com.hello.mimi.util.vo.FilePathMaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.w3c.dom.Text;

import java.io.IOException;

@Controller
@RequestMapping("/post")
public class PostUsedDTOController {
    @Autowired
    PostService postService;




    @ModelAttribute("postDTO")
    public PostDTO createPostDTO(@RequestParam String postType) {
        PostDTO postDTO = PostDTOFactory.createPostDTO(postType);
        System.out.println( "postDTO.hashCode() ---" + postDTO.hashCode() );
        return postDTO;
    }

    @GetMapping("/{postId}")
    public String readPost(@ModelAttribute("postDTO") PostDTO postDTO, @PathVariable Long postId, Model model) {
        postDTO = postService.readPost(postDTO);
        model.addAttribute("postDTO", postDTO);
        return "post/detail";
    }

    @GetMapping("/{postId}/update")
    public String updatePostForm(@PathVariable Long postId, @ModelAttribute("postDTO") PostDTO postDTO, Model model) {
        postDTO.setPostId(postId);
        String postType = postDTO.getPostType();
        postDTO = postService.readPost(postDTO);
        PostTypeEnum[] postTypeEnum = PostTypeEnum.values();

        model.addAttribute("postTypeEnum", postTypeEnum);
        model.addAttribute("postDTO", postDTO);
        return "post/create";
    }

    @PostMapping("/create")
    public String createPost(@ModelAttribute("postDTO") PostDTO postDTO, RedirectAttributes redirectAttributes) throws IOException {
        System.out.println( postDTO.hashCode() );


        postService.createPost(postDTO);

        redirectAttributes.addFlashAttribute("postDTO", postDTO);
        redirectAttributes.addAttribute("postId", postDTO.getPostId());

        return "redirect:{postId}";
    }

    @ResponseBody
    @PutMapping("/{postId}")
    public ResponseEntity<PostDTO> updatePost(@PathVariable Long postId, @ModelAttribute PostDTO postDTO) {
        postDTO.setPostId(postId);
        int updateCnt = postService.updatePost(postDTO);
        return ResponseEntity.ok(postDTO);
    }

    @ResponseBody
    @DeleteMapping("/{postId}")
    public ResponseEntity<PostDTO> deletePost(@PathVariable Long postId, @ModelAttribute PostDTO postDTO) {
        postDTO.setPostId(postId);
        int delCnt = postService.deletePost(postDTO);
        return ResponseEntity.ok(postDTO);
    }
}
