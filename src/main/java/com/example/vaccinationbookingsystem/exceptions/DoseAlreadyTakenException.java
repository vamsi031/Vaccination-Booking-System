package com.example.vaccinationbookingsystem.exceptions;

public class DoseAlreadyTakenException extends RuntimeException{
    public DoseAlreadyTakenException(String message){
        super(message);
    }
}
