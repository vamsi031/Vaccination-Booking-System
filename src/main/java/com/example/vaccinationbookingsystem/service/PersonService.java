package com.example.vaccinationbookingsystem.service;

import com.example.vaccinationbookingsystem.Dto.RequestDto.AddPersonRequestDto;
import com.example.vaccinationbookingsystem.Dto.RespnseDto.AddPersonResponseDto;
import com.example.vaccinationbookingsystem.Dto.RespnseDto.GetMalePersonResponseDto;
import com.example.vaccinationbookingsystem.model.Person;
import com.example.vaccinationbookingsystem.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;
    public AddPersonResponseDto addPerson(AddPersonRequestDto addPersonRequestDto) {
        //convert addPersonRequestDto to Person Entity
        Person person = new Person();
        person.setAadharNo(addPersonRequestDto.getAadharNo());
        person.setAge(addPersonRequestDto.getAge());
        person.setEmail(addPersonRequestDto.getEmail());
        person.setGender(addPersonRequestDto.getGender());
        person.setName(addPersonRequestDto.getName());

        Person savedPerson = personRepository.save(person);

        //person to responseDto
        AddPersonResponseDto addPersonResponseDto = new AddPersonResponseDto();
        addPersonResponseDto.setName(savedPerson.getName());
        addPersonResponseDto.setMessage("person added successfully");

        return addPersonResponseDto;


    }

    public String updateEmail(String oldEmail, String newEmail) {
        Person person = personRepository.findByEmail(oldEmail);
        person.setEmail(newEmail);
        personRepository.save(person);
        return "updated successfully";

    }

    public List<GetMalePersonResponseDto> getAllMaleOfAgeGreaterThan(int age) {
        List<GetMalePersonResponseDto> malePersonList = new ArrayList<>();
        List<Person> personList = personRepository.findAll();
        for(Person person:personList){

            String gender = String.valueOf(person.getGender());
            if(gender.equals("MALE") && person.getAge()>age){

                malePersonList.add(new GetMalePersonResponseDto(person.getId(), person.getName()));
            }
        }
        return malePersonList;
    }
}
