package com.example.foodbook.sevices;

import com.example.foodbook.dto.CommentDTO;
import com.example.foodbook.models.Comment;
import com.example.foodbook.repositories.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
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
    private final ModelMapper modelMapper;


    public void save(CommentDTO commentDTO) {
        var comment = new Comment();
        comment.setComment(commentDTO.getComment());
        comment.setPerson(personService.findById(commentDTO.getPersonId()));
        comment.setPost(postService.findPostById(commentDTO.getPostId()));
        commentRepository.save(comment);
    }

    public List<CommentDTO> getCommentsById(long postId) {
        var comments = commentRepository.findCommentByByPostId(postId);
        if (comments.isEmpty())
            return new ArrayList<>();
        return comments.stream().map(this::mapToCommentDTO).collect(Collectors.toList());
    }

    private CommentDTO mapToCommentDTO(Comment comment) {
        var commentDTO = modelMapper.map(comment, CommentDTO.class);
        commentDTO.setPostId(comment.getPost().getId());
        commentDTO.setPersonId(comment.getPerson().getId());
        commentDTO.setImage(comment.getPerson().getImage());
        return commentDTO;
    }

}
