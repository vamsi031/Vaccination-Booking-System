package com.example.vaccinationbookingsystem.controller;

import com.example.vaccinationbookingsystem.Dto.RequestDto.AddPersonRequestDto;
import com.example.vaccinationbookingsystem.Dto.RespnseDto.AddPersonResponseDto;
import com.example.vaccinationbookingsystem.Dto.RespnseDto.GetMalePersonResponseDto;
import com.example.vaccinationbookingsystem.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {
    @Autowired
    PersonService personService;


    @PostMapping("/add")
    public ResponseEntity addPerson(@RequestBody AddPersonRequestDto addPersonRequestDto){

        AddPersonResponseDto addPersonResponseDto = personService.addPerson(addPersonRequestDto);

        return new ResponseEntity(addPersonResponseDto, HttpStatus.CREATED);

    }

    @PutMapping("/update_email")
    public ResponseEntity updateEmail(@RequestParam String oldEmail,@RequestParam String newEmail){
        String response = personService.updateEmail(oldEmail,newEmail);

        return new ResponseEntity(response,HttpStatus.ACCEPTED);
    }

    @GetMapping("/get_persons_with_male_age_of")
    public ResponseEntity<List<GetMalePersonResponseDto>> getAllMaleOfAgeGreaterThan(@RequestParam int age){
        List<GetMalePersonResponseDto> malePersonsList = personService.getAllMaleOfAgeGreaterThan(age);

        return new ResponseEntity(malePersonsList,HttpStatus.ACCEPTED);
    }
}
