package com.example.vaccinationbookingsystem.controller;

import com.example.vaccinationbookingsystem.Dto.RequestDto.AddDoctorReqestDto;
import com.example.vaccinationbookingsystem.Dto.RespnseDto.AddDoctorResponseDto;
import com.example.vaccinationbookingsystem.Dto.RespnseDto.DoctorResponseDto;
import com.example.vaccinationbookingsystem.Enum.CenterType;
import com.example.vaccinationbookingsystem.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DoctorController {
    @Autowired
    DoctorService doctorService;

    @PostMapping("/add")
    public ResponseEntity addDoctor(@RequestBody AddDoctorReqestDto addDoctorReqestDto){
        try {
            AddDoctorResponseDto addDoctorResponseDto = doctorService.addDoctor(addDoctorReqestDto);
            return new ResponseEntity(addDoctorResponseDto, HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/get_by_age_greater_than")
    public ResponseEntity getByAgeGreaterThan(@RequestParam("age") int age){
        List<DoctorResponseDto> doctors = doctorService.getByAgeGreaterThan(age);

        return new ResponseEntity(doctors,HttpStatus.ACCEPTED);
    }

    // get the doctor with highest number of appointments
    @GetMapping("/get_doctor_with_highest_appointments")
    public ResponseEntity getDoctorWithHighestNumberOfAppointments(){
        try {
            DoctorResponseDto doctorResponseDto = doctorService.getDoctorWithHighestNumberOfAppointments();
            return new ResponseEntity(doctorResponseDto,HttpStatus.ACCEPTED);
        }catch(Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    // get the list of doctors who belong to a particular center
    @GetMapping("/get_doctors_belong_this_center")
    public ResponseEntity getDoctorsWhoBelongToThisCenter(@RequestParam String centerType){
        try {
            List<DoctorResponseDto> doctorList = doctorService.getDoctorsWhoBelongToThisCenter(centerType);
            return new ResponseEntity(doctorList,HttpStatus.ACCEPTED);
        }catch(Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    // ---api to update email and/or age of a doctor


}
