package com.example.vaccinationbookingsystem.exceptions;

public class DoseOneNotTakenException extends RuntimeException{
    public DoseOneNotTakenException(String message){
        super(message);
    }
}
