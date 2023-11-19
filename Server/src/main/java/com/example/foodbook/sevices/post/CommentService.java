package com.example.foodbook.sevices.post;

import com.example.foodbook.dto.post.info.CommentDTO;
import com.example.foodbook.mapper.LocalMapper;
import com.example.foodbook.models.post.info.Comment;
import com.example.foodbook.repositories.post.info.CommentRepository;
import com.example.foodbook.sevices.person.PersonService;
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
