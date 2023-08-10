package com.example.vaccinationbookingsystem.controller;

import com.example.vaccinationbookingsystem.Dto.RequestDto.AddCenterRequestDto;
import com.example.vaccinationbookingsystem.Dto.RespnseDto.AddCenterResponseDto;
import com.example.vaccinationbookingsystem.Dto.RespnseDto.CenterResponseDto;
import com.example.vaccinationbookingsystem.Dto.RespnseDto.DoctorResponseDto;
import com.example.vaccinationbookingsystem.Enum.CenterType;
import com.example.vaccinationbookingsystem.service.VaccinationCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vaccination-center")
public class VaccinationCenterController {
    @Autowired
    VaccinationCenterService vaccinationCenterService;

    @PostMapping("/add")
    public ResponseEntity addCenter(@RequestBody AddCenterRequestDto addCenterRequestDto){
        try{
            AddCenterResponseDto addCenterResponseDto = vaccinationCenterService.addCenter(addCenterRequestDto);
            return new ResponseEntity(addCenterResponseDto, HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    // get all the doctors at centers of a particular centerType
    @GetMapping("/get_all_doctors_of_this_center")
    public ResponseEntity getAllDoctorOfThisCenter(@RequestParam CenterType centerType){
        List<DoctorResponseDto> doctorResponseDtoList = vaccinationCenterService.getAllDoctorOfThisCenter(centerType);

        return new ResponseEntity(doctorResponseDtoList,HttpStatus.ACCEPTED);
    }

    // get the center with highest number of doctors
    @GetMapping("/get_center_with_highest_no_of_doctors")
    public ResponseEntity getCenterWithHighestNoOfDoctors(){
        CenterResponseDto centerResponseDto = vaccinationCenterService.getCenterWithHighestNoOfDoctors();

        return new ResponseEntity(centerResponseDto,HttpStatus.ACCEPTED);
    }

    // get the center with highest number of doctors among a particular centerType
    @GetMapping("/get_center_with_highest_no_of_doctors_in_this_center_type")
    public ResponseEntity getCenterWithHighestNoOfDoctorsInThisCenterType(@RequestParam CenterType centerType){
        CenterResponseDto centerResponseDto = vaccinationCenterService.getCenterWithHighestNoOfDoctorsInThisCenterType(centerType);

        return new ResponseEntity(centerResponseDto,HttpStatus.ACCEPTED);
    }
}
