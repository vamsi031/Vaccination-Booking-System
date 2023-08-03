package com.example.vaccinationbookingsystem.controller;

import com.example.vaccinationbookingsystem.Dto.RequestDto.AddDoctorReqestDto;
import com.example.vaccinationbookingsystem.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DoctorController {
    @Autowired
    DoctorService doctorService;

    @PostMapping("/add")
    public ResponseEntity addDoctor(@RequestBody AddDoctorReqestDto addDoctorReqestDto){

        doctorService.addDoctor(addDoctorReqestDto);

        return new ResponseEntity("doctor added", HttpStatus.CREATED);

    }

}
