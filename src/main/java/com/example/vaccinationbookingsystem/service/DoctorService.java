package com.example.vaccinationbookingsystem.service;

import com.example.vaccinationbookingsystem.Dto.RequestDto.AddDoctorReqestDto;
import com.example.vaccinationbookingsystem.Dto.RespnseDto.AddDoctorResponseDto;
import com.example.vaccinationbookingsystem.Dto.RespnseDto.AddPersonResponseDto;
import com.example.vaccinationbookingsystem.Dto.RespnseDto.DoctorResponseDto;
import com.example.vaccinationbookingsystem.Enum.CenterType;
import com.example.vaccinationbookingsystem.exceptions.DoctorNotFoundException;
import com.example.vaccinationbookingsystem.model.Doctor;
import com.example.vaccinationbookingsystem.model.VaccinationCenter;
import com.example.vaccinationbookingsystem.repository.DoctorRepository;
import com.example.vaccinationbookingsystem.repository.VaccinationCenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {
    @Autowired
    DoctorRepository doctorRepository;
    @Autowired
    VaccinationCenterRepository vaccinationCenterRepository;

    public AddDoctorResponseDto addDoctor(AddDoctorReqestDto addDoctorReqestDto) {

        Optional<VaccinationCenter> optionalVaccinationCenter = vaccinationCenterRepository.findById(addDoctorReqestDto.getCenterId());
        if(optionalVaccinationCenter.isEmpty())throw new DoctorNotFoundException("wrong doctor id");

        VaccinationCenter vaccinationCenter = optionalVaccinationCenter.get();
        Doctor doctor = new Doctor();
        doctor.setAge(addDoctorReqestDto.getAge());
        doctor.setEmailId(addDoctorReqestDto.getEmail());
        doctor.setGender(addDoctorReqestDto.getGender());
        doctor.setCenter(vaccinationCenter);

        vaccinationCenter.getDoctors().add(doctor);

        VaccinationCenter savedVaccinationCenter = vaccinationCenterRepository.save(vaccinationCenter);

        //doctor to AddDoctorResponseDto
        AddDoctorResponseDto addDoctorResponseDto = new AddDoctorResponseDto();
        addDoctorResponseDto.setName(doctor.getName());
        addDoctorResponseDto.setMessage("doctor added successfully");

        return addDoctorResponseDto;


    }


    public List<DoctorResponseDto> getByAgeGreaterThan(int age) {
        List<Doctor> doctorsList = doctorRepository.findAll();
        List<DoctorResponseDto> list = new ArrayList<>();
        for(Doctor doctor:doctorsList){
            if(doctor.getAge()>age){
               list.add(new DoctorResponseDto(doctor.getName(),doctor.getId()));
            }
        }

        return list;

    }

    public DoctorResponseDto getDoctorWithHighestNumberOfAppointments() {
        List<Doctor> doctorList = doctorRepository.findAll();
        if(doctorList.isEmpty())throw new DoctorNotFoundException("doctor not found");
        Doctor ansDoctor=null;
        int count=0;
        for(Doctor doctor:doctorList) {
            if (doctor.getAppointmentList().size() > count) {
                count = doctor.getAppointmentList().size();
                ansDoctor = doctor;

            }
        }
        DoctorResponseDto doctorResponseDto = new DoctorResponseDto();
        doctorResponseDto.setDoctorId(ansDoctor.getId());
        doctorResponseDto.setName(ansDoctor.getName());

       return doctorResponseDto;
    }

    public List<DoctorResponseDto> getDoctorsWhoBelongToThisCenter(String centerType) {
        List<DoctorResponseDto> list = new ArrayList<>();
        List<Doctor> doctorList = doctorRepository.findAll();
        for(Doctor doctor:doctorList){
            if(doctor.getCenter().equals(centerType)){
                list.add(new DoctorResponseDto(doctor.getName(),doctor.getId()));
            }
        }

        return list;
    }
    }
