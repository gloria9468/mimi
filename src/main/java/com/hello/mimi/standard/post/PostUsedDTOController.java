package com.hello.mimi.standard.post;

import com.hello.mimi.standard.post.model.*;
import com.hello.mimi.standard.post.service.PostService;
import com.hello.mimi.util.PostTypeEnum;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.w3c.dom.ls.LSOutput;

import java.beans.PropertyEditorSupport;
import java.io.IOException;

import static java.awt.SystemColor.text;

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
        String postType = postDTO.getPostType();
        postDTO = postService.readPost(postType, postId);

        //Object postDTOInstance = PostDTOFactory.convertPostDTO(postDTO);
        //model.addAttribute("postDTO", postDTOInstance);
        model.addAttribute("postDTO", postDTO);

        return "post/detail";
    }

    @GetMapping("/{postId}/update")
    public String updatePostForm(@PathVariable Long postId, @ModelAttribute("postDTO") PostDTO postDTO, Model model) {
        postDTO.setPostId(postId);
        String postType = postDTO.getPostType();
        postDTO = postService.readPost(postType, postId);
        PostTypeEnum[] postTypeEnum = PostTypeEnum.values();

        model.addAttribute("postTypeEnum", postTypeEnum);
        model.addAttribute("postDTO", postDTO);
        return "post/create";
    }

    @PostMapping("/create")
    public String createPost(@ModelAttribute("postDTO") PostDTO postDTO, RedirectAttributes redirectAttributes) throws IOException {
        System.out.println( postDTO.hashCode() );

        if (postDTO instanceof PhotoPostDTO) {
            PostDTO pDTO = (PostDTO) postDTO;

            //int createTextPostCnt = postService.createPost( pDTO );
            postDTO = PostDTOFactory.makePhotoDir((PhotoPostDTO) postDTO);
        }

        int createCnt = postService.createPost(postDTO);
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
