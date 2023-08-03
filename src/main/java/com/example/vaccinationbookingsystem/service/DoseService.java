package com.example.vaccinationbookingsystem.service;

import com.example.vaccinationbookingsystem.Dto.RequestDto.BookDoseOneRequestDto;
import com.example.vaccinationbookingsystem.Dto.RequestDto.BookDoseTwoRequestDto;
import com.example.vaccinationbookingsystem.Dto.RespnseDto.BookDoseOneResponseDto;
import com.example.vaccinationbookingsystem.Dto.RespnseDto.BookDoseTwoResponseDto;
import com.example.vaccinationbookingsystem.Enum.DoseType;
import com.example.vaccinationbookingsystem.exceptions.DoseAlreadyTakenException;
import com.example.vaccinationbookingsystem.exceptions.DoseOneNotTakenException;
import com.example.vaccinationbookingsystem.exceptions.PersonNotFoundException;
import com.example.vaccinationbookingsystem.model.Dose;
import com.example.vaccinationbookingsystem.model.Person;
import com.example.vaccinationbookingsystem.repository.DoseRepository;
import com.example.vaccinationbookingsystem.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class DoseService {
    @Autowired
    DoseRepository doseRepository;
    @Autowired
    PersonRepository personRepository;
    public BookDoseOneResponseDto getDoseOne(BookDoseOneRequestDto bookDoseOneRequestDto) {
        Optional<Person> optionalPerson = personRepository.findById(bookDoseOneRequestDto.getPersonId());
        if(optionalPerson.isEmpty()) throw new PersonNotFoundException("person not found");
        Person person  = optionalPerson.get();
        if(person.isDoseOneTaken())throw new DoseAlreadyTakenException("dose alreay taken");
        Dose dose = new Dose();
        dose.setDoseId(String.valueOf(UUID.randomUUID()));
        dose.setDoseType(bookDoseOneRequestDto.getDoseType());
        dose.setPerson(person);

        person.setDoseOneTaken(true);
        person.getDoseList().add(dose);

        personRepository.save(person);
        BookDoseOneResponseDto bookDoseOneResponseDto = new BookDoseOneResponseDto();
        bookDoseOneResponseDto.setDoseId(dose.getDoseId());
        bookDoseOneResponseDto.setMessage("dose one taken successfully");

        return bookDoseOneResponseDto;
    }

    public BookDoseTwoResponseDto getDoseTwo(BookDoseTwoRequestDto bookDoseTwoRequestDto) {
        Optional<Person> optionalPerson = personRepository.findById(bookDoseTwoRequestDto.getPersonId());
        if(optionalPerson.isEmpty())throw new PersonNotFoundException("person not found");
        Person person = optionalPerson.get();
        if(!person.isDoseOneTaken())throw new DoseOneNotTakenException("dose one not taken");
        if(person.isDoseTwoTaken())throw new DoseAlreadyTakenException("dose two already taken");

        person.setDoseTwoTaken(true);

        Dose dose = new Dose();
        dose.setDoseId(String.valueOf(UUID.randomUUID()));
        dose.setDoseType(bookDoseTwoRequestDto.getDoseType());
        dose.setPerson(person);

        BookDoseTwoResponseDto bookDoseTwoResponseDto = new BookDoseTwoResponseDto();
        bookDoseTwoResponseDto.setDoseId(dose.getDoseId());
        bookDoseTwoResponseDto.setMessage("dose two taken successfully");

        personRepository.save(person);

        return bookDoseTwoResponseDto;


    }
}
