package com.example.foodbook.dto;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EquipmentApiDTO {
    //https://api.spoonacular.com/recipes/1131211/equipmentWidget.json?apiKey=f3a620d7c1d545c995304d7e6efe0e3a
    String name;
    String image;

}
