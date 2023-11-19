package com.example.foodbook.dto;
import com.example.foodbook.dto.person.PersonDTO;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

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
    List<CommentDTO> commentList;
    Integer postLikes;
}