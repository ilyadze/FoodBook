package com.example.foodbook.controllers;

import com.example.foodbook.dto.CommentDTO;
import com.example.foodbook.exceptions.CommentException;
import com.example.foodbook.sevices.CommentService;
import com.example.foodbook.utils.ErrorMessages;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/create")
    public ResponseEntity<HttpStatus> createComment(@RequestBody @Valid CommentDTO commentDTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            String message =  String.join(", ", ErrorMessages.getErrorMessages(bindingResult));
            throw new CommentException(HttpStatus.BAD_REQUEST, message);
        }
        commentService.save(commentDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


}
