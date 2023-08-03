package com.example.vaccinationbookingsystem.service;

import com.example.vaccinationbookingsystem.Dto.RequestDto.AddDoctorReqestDto;
import com.example.vaccinationbookingsystem.model.Appointment;
import com.example.vaccinationbookingsystem.model.Doctor;
import com.example.vaccinationbookingsystem.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoctorService {
    @Autowired
    DoctorRepository doctorRepository;
    public void addDoctor(AddDoctorReqestDto addDoctorReqestDto) {

        Doctor doctor = new Doctor();
        doctor.setAge(addDoctorReqestDto.getAge());
        doctor.setEmailId(addDoctorReqestDto.getEmail());
        doctor.setGender(addDoctorReqestDto.getGender());

        doctorRepository.save(doctor);
    }
}
