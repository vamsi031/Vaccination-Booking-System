package com.example.vaccinationbookingsystem.exceptions;

public class PersonNotFoundException extends RuntimeException{
    public PersonNotFoundException(String message){
        super(message);
    }
}
