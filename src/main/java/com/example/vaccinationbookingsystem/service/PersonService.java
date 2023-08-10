package com.example.vaccinationbookingsystem.service;

import com.example.vaccinationbookingsystem.Dto.RequestDto.AddPersonRequestDto;
import com.example.vaccinationbookingsystem.Dto.RespnseDto.AddPersonResponseDto;
import com.example.vaccinationbookingsystem.Dto.RespnseDto.GetPersonResponseDto;
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

    public List<GetPersonResponseDto> getAllMaleOfAgeGreaterThan(int age) {
        List<GetPersonResponseDto> malePersonList = new ArrayList<>();
        List<Person> personList = personRepository.findAll();
        for(Person person:personList){

            String gender = String.valueOf(person.getGender());
            if(gender.equals("MALE") && person.getAge()>age){

                malePersonList.add(new GetPersonResponseDto(person.getId(), person.getName(),person.getGender()));
            }
        }
        return malePersonList;
    }

    public List<GetPersonResponseDto> getAllFemalesOnlytakenDose1(int age) {
        List<GetPersonResponseDto>  list = new ArrayList<>();
        List<Person> personsList = personRepository.findAll();
        for(Person person:personsList){
            if(person.isDoseTwoTaken() && !person.isDoseTwoTaken()){
                list.add(new GetPersonResponseDto(person.getId(), person.getName(),person.getGender()));
            }
        }
        return list;
    }

    public List<GetPersonResponseDto> getFullyVaccinatedPersons() {
        List<GetPersonResponseDto> list = new ArrayList<>();
        List<Person> personsList = personRepository.findAll();
        for(Person person:personsList){
            if(person.isDoseTwoTaken() && person.isDoseTwoTaken()){
                list.add(new GetPersonResponseDto(person.getId(), person.getName(),person.getGender()));
            }
        }
        return list;
    }

    public List<GetPersonResponseDto> getNotVaccinatedPersons() {
        List<GetPersonResponseDto> list = new ArrayList<>();
        List<Person> personsList = personRepository.findAll();
        for(Person person:personsList){
            if(!person.isDoseTwoTaken() && !person.isDoseTwoTaken()){
                list.add(new GetPersonResponseDto(person.getId(), person.getName(),person.getGender()));
            }
        }
        return list;
    }

    public List<GetPersonResponseDto> getFemaleWhoseAgeGreaterThanAndHaveDose1(int age) {
        List<GetPersonResponseDto> list = new ArrayList<>();
        List<Person> personsList = personRepository.findAll();
        for(Person person:personsList){
            if(person.getGender().equals("FEMALE") && person.isDoseTwoTaken()
                    && !person.isDoseTwoTaken() && person.getAge()>age){
                list.add(new GetPersonResponseDto(person.getId(), person.getName(),person.getGender()));
            }
        }
        return list;
    }

    public List<GetPersonResponseDto> getAllMaleWhoseAgeGreatherThan(int age) {
        List<GetPersonResponseDto> list = new ArrayList<>();
        List<Person> personsList = personRepository.findAll();
        for(Person person:personsList){
            if(person.getGender().equals("MALE") && person.isDoseTwoTaken()
                    && person.isDoseTwoTaken() && person.getAge()>age){
                list.add(new GetPersonResponseDto(person.getId(), person.getName(),person.getGender()));
            }
        }
        return list;
    }
}
