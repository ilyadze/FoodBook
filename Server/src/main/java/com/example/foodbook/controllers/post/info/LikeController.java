package com.example.foodbook.controllers.post.info;

import com.example.foodbook.sevices.post.info.PostLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/like")
public class LikeController {

    private final PostLikeService postLikeService;

    @PostMapping("/add/{post_id}")
    public ResponseEntity<?> addLike(@PathVariable Long post_id, Principal principal) {
        postLikeService.addLike(post_id, principal.getName());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/amount/{post_id}")
    public ResponseEntity<?> showPostsLikes(@PathVariable Long post_id) {
        return new ResponseEntity<>(postLikeService.countAllByPostId(post_id), HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<?> showUserLikes(Principal principal){
        return new ResponseEntity<>(postLikeService.getUserLikes(principal.getName()),HttpStatus.OK);
    }

}
