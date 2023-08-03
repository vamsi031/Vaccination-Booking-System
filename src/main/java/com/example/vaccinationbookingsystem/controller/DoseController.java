package com.example.vaccinationbookingsystem.controller;

import com.example.vaccinationbookingsystem.Dto.RequestDto.BookDoseOneRequestDto;
import com.example.vaccinationbookingsystem.Dto.RequestDto.BookDoseTwoRequestDto;
import com.example.vaccinationbookingsystem.Dto.RespnseDto.BookDoseOneResponseDto;
import com.example.vaccinationbookingsystem.Dto.RespnseDto.BookDoseTwoResponseDto;
import com.example.vaccinationbookingsystem.service.DoseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dose")
public class DoseController {
    @Autowired
    DoseService doseService;

    @PutMapping("/get_dose_one")
    public ResponseEntity getDoseOne(@RequestBody BookDoseOneRequestDto bookDoseOneRequestDto){
        try {
            BookDoseOneResponseDto bookDoseOneResponseDto = doseService.getDoseOne(bookDoseOneRequestDto);
            return new ResponseEntity(bookDoseOneResponseDto,HttpStatus.ACCEPTED);
        }catch(Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping("/get_dose_two")
    public ResponseEntity getDoseTwo(@RequestBody BookDoseTwoRequestDto bookDoseTwoRequestDto){
        try{
            BookDoseTwoResponseDto bookDoseTwoResponseDto = doseService.getDoseTwo(bookDoseTwoRequestDto);
            return new ResponseEntity(bookDoseTwoResponseDto,HttpStatus.ACCEPTED);
        }catch(Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
