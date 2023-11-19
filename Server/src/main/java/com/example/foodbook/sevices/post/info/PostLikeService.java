package com.example.foodbook.sevices.post.info;

import com.example.foodbook.dto.post.PostDTO;
import com.example.foodbook.mapper.LocalMapper;
import com.example.foodbook.models.post.info.PostLike;
import com.example.foodbook.repositories.post.info.PostLikeRepository;
import com.example.foodbook.sevices.person.PersonService;
import com.example.foodbook.sevices.post.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostLikeService {

    private final PostLikeRepository postLikeRepository;
    private final PostService postService;
    private final PersonService personService;
    private final LocalMapper localMapper;

    public void addLike(long postId, String username) {

        var like = new PostLike();
        like.setPost(postService.findPostById(postId));
        like.setPerson(personService.findByUsername(username));

        postLikeRepository.save(like);
    }

    public List<PostDTO> getUserLikes(String username) {

        return postLikeRepository.findPostLikeByPersonUsername(username)
                .map(likes -> likes.stream()
                        .map(PostLike::getPost)
                        .map(localMapper::convertPostToDto)
                        .collect(Collectors.toList()))
                .orElseGet(ArrayList::new);
    }

    public int countAllByPostId(Long postId) {
        return postLikeRepository.countAllByPostId(postId);
    }

}
