package com.example.foodbook.sevices;

import com.example.foodbook.dto.CommentDTO;
import com.example.foodbook.models.Comment;
import com.example.foodbook.repositories.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PersonService personService;
    private final PostService postService;

    public void save(CommentDTO commentDTO) {
        System.out.println(commentDTO);
        var comment = new Comment();
        comment.setComment(commentDTO.getComment());
        var person = personService.findById(commentDTO.getPersonId());
        comment.setPerson(person);
        comment.setPost(postService.findPostById(commentDTO.getPostId()));
        commentRepository.save(comment);
    }
}
