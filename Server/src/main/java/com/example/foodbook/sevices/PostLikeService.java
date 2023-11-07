package com.example.foodbook.sevices;

import com.example.foodbook.models.PostLike;
import com.example.foodbook.repositories.PostLikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostLikeService {

    private final PostLikeRepository postLikeRepository;
    private final PostService postService;
    private final PersonService personService;

    public void addLike(long postId, String username) {

        var like = new PostLike();
        like.setPost(postService.findPostById(postId));
        like.setPerson(personService.findByUsername(username));

        postLikeRepository.save(like);
    }

    public int countAllByPostId(Long postId) {
         return postLikeRepository.countAllByPostId(postId);
    }

}
