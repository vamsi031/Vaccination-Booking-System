package com.example.vaccinationbookingsystem.Dto.RequestDto;

import com.example.vaccinationbookingsystem.Enum.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AddPersonRequestDto {

    String aadharNo;

    String name;

    int age;


    String email;


    Gender gender;


    String mobileNo;
}
