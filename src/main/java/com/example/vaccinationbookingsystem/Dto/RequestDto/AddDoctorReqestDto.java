package com.example.vaccinationbookingsystem.Dto.RequestDto;

import com.example.vaccinationbookingsystem.Enum.Gender;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AddDoctorReqestDto {

    int age;
    String name;
    String email;
    @Enumerated(EnumType.STRING)
    Gender gender;

}
