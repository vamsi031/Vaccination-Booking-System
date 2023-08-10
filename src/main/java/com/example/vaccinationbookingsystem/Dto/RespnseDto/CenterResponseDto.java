package com.example.vaccinationbookingsystem.Dto.RespnseDto;

import com.example.vaccinationbookingsystem.Enum.CenterType;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CenterResponseDto {

    int centerId;
    String centerName;
    CenterType centerType;
    String address;

}
