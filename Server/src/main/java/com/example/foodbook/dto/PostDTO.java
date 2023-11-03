package com.example.foodbook.dto;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@NotNull
public class PostDTO {
    String id;
    FullRecipeAPIDTO recipe;
    PersonDTO person;
    //TODO
    /*List<CommentDTO> commenList;
    List<PostLike> postLikes;*/
}