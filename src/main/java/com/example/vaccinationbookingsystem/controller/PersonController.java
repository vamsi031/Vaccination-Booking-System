package com.example.vaccinationbookingsystem.controller;

import com.example.vaccinationbookingsystem.Dto.RequestDto.AddPersonRequestDto;
import com.example.vaccinationbookingsystem.Dto.RespnseDto.AddPersonResponseDto;
import com.example.vaccinationbookingsystem.Dto.RespnseDto.GetPersonResponseDto;
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

    // get all males of age greater than a certain age
    @GetMapping("/get_persons_with_male_age_of")
    public ResponseEntity<List<GetPersonResponseDto>> getAllMaleOfAgeGreaterThan(@RequestParam int age){
        List<GetPersonResponseDto> malePersonsList = personService.getAllMaleOfAgeGreaterThan(age);

        return new ResponseEntity(malePersonsList,HttpStatus.ACCEPTED);
    }

    // get all females who have taken only dose 1 not dose 2
    @GetMapping("/get_all_females_only_taken_dose1")
    public ResponseEntity getAllFemalesOnlytakenDose1(@RequestParam int age){
        List<GetPersonResponseDto> femalePersonsList = personService.getAllFemalesOnlytakenDose1(age);

        return new ResponseEntity(femalePersonsList,HttpStatus.ACCEPTED);
    }

    // get all the people who are fully vaccinated
    @GetMapping("/get_fully_vaccinated_persons")
    public ResponseEntity getFullyVaccinatedPersons(){
        List<GetPersonResponseDto> personsList = personService.getFullyVaccinatedPersons();

        return new ResponseEntity(personsList,HttpStatus.ACCEPTED);
    }

    // get all the people who have not taken even a single dose
    @GetMapping("/get_not_vaccinated_persons")
    public ResponseEntity getNotVaccinatedPersons(){
        List<GetPersonResponseDto> personsList = personService.getNotVaccinatedPersons();

        return new ResponseEntity(personsList,HttpStatus.ACCEPTED);
    }


    // get all females whose age is greater than a particular age and who have taken only dose 1
    @GetMapping("/female_whose_age_greater_than_and_have_dose1")
    public ResponseEntity getFemaleWhoseAgeGreaterThanAndHaveDose1(@RequestParam int age){
        List<GetPersonResponseDto> list = personService.getFemaleWhoseAgeGreaterThanAndHaveDose1(age);

        return new ResponseEntity(list,HttpStatus.ACCEPTED);
    }

    // get all males whose age is greater than a particular age and who have taken both
    @GetMapping("/get_all_male_whose_age_greater_than")
    public ResponseEntity getAllMaleWhoseAgeGreatherThan(@RequestParam int age){
        List<GetPersonResponseDto> list = personService.getAllMaleWhoseAgeGreatherThan(age);

        return new ResponseEntity(list,HttpStatus.ACCEPTED);
    }
}
