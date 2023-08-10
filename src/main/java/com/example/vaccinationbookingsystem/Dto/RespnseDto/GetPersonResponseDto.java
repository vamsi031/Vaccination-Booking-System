package com.example.vaccinationbookingsystem.Dto.RespnseDto;

import com.example.vaccinationbookingsystem.Enum.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GetPersonResponseDto {
    int id;
    String name;
    Gender gender;
}
