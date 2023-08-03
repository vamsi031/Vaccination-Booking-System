package com.example.vaccinationbookingsystem.controller;

import com.example.vaccinationbookingsystem.service.VaccinationCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VaccinationCenterController {
    @Autowired
    VaccinationCenterService vaccinationCenterService;
}
