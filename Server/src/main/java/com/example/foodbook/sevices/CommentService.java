package com.example.foodbook.sevices;

import com.example.foodbook.dto.CommentDTO;
import com.example.foodbook.mapper.LocalMapper;
import com.example.foodbook.models.Comment;
import com.example.foodbook.repositories.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PersonService personService;
    private final PostService postService;
    private final LocalMapper localMapper;

    public void save(CommentDTO commentDTO) {
        var comment = new Comment();
        comment.setComment(commentDTO.getComment());
        comment.setPerson(personService.findByUsername(commentDTO.getUsername()));
        comment.setPost(postService.findPostById(commentDTO.getPostId()));
        commentRepository.save(comment);
    }

    public List<CommentDTO> getCommentsById(long postId) {
        var comments = commentRepository.findCommentByPostId(postId);
        if (comments.isEmpty()) return new ArrayList<>();
        return comments.stream().map(localMapper::mapToCommentDTO).collect(Collectors.toList());
    }


}
