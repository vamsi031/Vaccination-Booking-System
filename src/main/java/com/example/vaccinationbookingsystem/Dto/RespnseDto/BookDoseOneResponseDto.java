package com.example.vaccinationbookingsystem.Dto.RespnseDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BookDoseOneResponseDto {
    String doseId;
    String message;
}
