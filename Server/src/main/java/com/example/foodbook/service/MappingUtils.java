package com.example.foodbook.service;

import com.example.foodbook.dto.PersonDTO;
import com.example.foodbook.dto.PostDTO;
import com.example.foodbook.models.Person;
import com.example.foodbook.models.Post;
import org.springframework.stereotype.Service;

@Service
public class MappingUtils {
    public Post mapToPostModel(PostDTO postDTO){
        Post post = new Post();

        post.setId(post.getId());
        post.setPostLikeList(postDTO.getPostLikeList());
        post.setPostDislikeList(postDTO.getPostDislikeList());
        post.setPerson(postDTO.getPerson());
        post.setImage(postDTO.getImage());
        post.setRecipe(postDTO.getRecipe());
        post.setCommentList(postDTO.getCommentList());

        return post;

    }
    public PostDTO mapToPostDTO(Post post){
        PostDTO postDTO = new PostDTO();

        postDTO.setId(post.getId());
        postDTO.setPostLikeList(post.getPostLikeList());
        postDTO.setPostDislikeList(post.getPostDislikeList());
        postDTO.setPerson(post.getPerson());
        postDTO.setImage(post.getImage());
        postDTO.setRecipe(post.getRecipe());
        postDTO.setCommentList(post.getCommentList());

        return postDTO;

    }
    public Person mapToPeronModel(PersonDTO personDTO){
        Person person = new Person();
        person.setId(personDTO.getId());
        person.setPostLikeList(personDTO.getPostLikeList());
        person.setPostDislikeList(personDTO.getPostDislikeList());
        person.setCommentList(personDTO.getCommentList());
        person.setEmail(personDTO.getEmail());
        person.setDescription(personDTO.getDescription());
        person.setPassword(personDTO.getPassword());
        person.setUsername(personDTO.getUsername());
        person.setHistorySearchList(personDTO.getHistorySearchList());
        person.setPostList(personDTO.getPostList());
        person.setFollowerList(personDTO.getFollowerList());
        person.setFollowingList(personDTO.getFollowingList());
        person.setPrivacyList(personDTO.getPrivacyList());
        person.setNotOurPrivacyList(personDTO.getNotOurPrivacyList());
        return person;

    }
    public PersonDTO mapToPeronDTO(Person person){
        PersonDTO personDTO = new PersonDTO();
        personDTO.setId(person.getId());
        personDTO.setPostLikeList(person.getPostLikeList());
        personDTO.setPostDislikeList(person.getPostDislikeList());
        personDTO.setCommentList(person.getCommentList());
        personDTO.setEmail(person.getEmail());
        personDTO.setDescription(person.getDescription());
        personDTO.setPassword(person.getPassword());
        personDTO.setUsername(person.getUsername());
        personDTO.setHistorySearchList(person.getHistorySearchList());
        personDTO.setPostList(person.getPostList());
        personDTO.setFollowerList(person.getFollowerList());
        personDTO.setFollowingList(person.getFollowingList());
        personDTO.setPrivacyList(person.getPrivacyList());
        personDTO.setNotOurPrivacyList(person.getNotOurPrivacyList());
        return personDTO;

    }
}
