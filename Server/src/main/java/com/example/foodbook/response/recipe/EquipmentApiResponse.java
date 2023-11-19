package com.example.foodbook.response.recipe;


import com.example.foodbook.dto.post.recipe.EquipmentApiDTO;
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
public class EquipmentApiResponse {
    List<EquipmentApiDTO> equipment;
}
