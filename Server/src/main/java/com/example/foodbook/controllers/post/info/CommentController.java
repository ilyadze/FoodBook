package com.example.foodbook.controllers.post.info;

import com.example.foodbook.dto.post.info.CommentDTO;
import com.example.foodbook.exceptions.CommentException;
import com.example.foodbook.sevices.post.CommentService;
import com.example.foodbook.utils.ErrorMessages;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {
    private final CommentService commentService;
    @PostMapping("/create")
    public ResponseEntity<HttpStatus> createComment(@RequestBody @Valid CommentDTO commentDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            var message = String.join(", ", ErrorMessages.getErrorMessages(bindingResult));
            throw new CommentException(HttpStatus.BAD_REQUEST, message);
        }
        commentService.save(commentDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping("/show/{post_id}")
    public ResponseEntity<?> getComments(@PathVariable Long post_id) {
        return new ResponseEntity<>(commentService.getCommentsById(post_id), HttpStatus.OK);
    }

}
