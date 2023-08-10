package com.example.vaccinationbookingsystem.controller;

import com.example.vaccinationbookingsystem.Dto.RequestDto.BookAppointmentRequestDto;
import com.example.vaccinationbookingsystem.Dto.RespnseDto.AddDoctorResponseDto;
import com.example.vaccinationbookingsystem.Dto.RespnseDto.AddPersonResponseDto;
import com.example.vaccinationbookingsystem.Dto.RespnseDto.BookAppointmentResponseDto;
import com.example.vaccinationbookingsystem.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("appointment")
public class AppointmentController {
    @Autowired
    AppointmentService appointmentService;

    @PutMapping("/bookAppointment")
    public ResponseEntity bookAppointment(@RequestBody BookAppointmentRequestDto bookAppointmentRequestDto){
        try{
            BookAppointmentResponseDto bookAppointmentResponseDto = appointmentService.bookAppointment(bookAppointmentRequestDto);
            return new ResponseEntity(bookAppointmentResponseDto, HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    // get all the appointments of a particular doctor
    @GetMapping("/all_appointments_of_doctor")
    public ResponseEntity getAllAppointmentsOfADoctor(@RequestParam int doctorId){
        try{
            List<BookAppointmentResponseDto> list = appointmentService.getAllAppointmentsOfADoctor(doctorId);

            return new ResponseEntity(list,HttpStatus.ACCEPTED);
        }catch(Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    // get all the appointments for a particular person
    @GetMapping("/all_appointments_of_person")
    public ResponseEntity getAllAppointmentsOfAPerson(@RequestParam int personId){
        try{
            List<BookAppointmentResponseDto> list = appointmentService.getAllAppointmentsOfAPerson(personId);

            return new ResponseEntity(list,HttpStatus.ACCEPTED);
        }catch(Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

}
