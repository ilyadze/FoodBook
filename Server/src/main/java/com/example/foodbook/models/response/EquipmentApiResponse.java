package com.example.foodbook.models.response;


import com.example.foodbook.dto.EquipmentApiDTO;
import com.example.foodbook.models.Equipment;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.awt.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EquipmentApiResponse {

    List<EquipmentApiDTO> equipment;
}
