package com.example.vaccinationbookingsystem.service;

import com.example.vaccinationbookingsystem.Dto.RequestDto.AddCenterRequestDto;
import com.example.vaccinationbookingsystem.Dto.RespnseDto.AddCenterResponseDto;
import com.example.vaccinationbookingsystem.Dto.RespnseDto.CenterResponseDto;
import com.example.vaccinationbookingsystem.Dto.RespnseDto.DoctorResponseDto;
import com.example.vaccinationbookingsystem.Enum.CenterType;
import com.example.vaccinationbookingsystem.model.Doctor;
import com.example.vaccinationbookingsystem.model.VaccinationCenter;
import com.example.vaccinationbookingsystem.repository.DoctorRepository;
import com.example.vaccinationbookingsystem.repository.VaccinationCenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VaccinationCenterService {
    @Autowired
    VaccinationCenterRepository vaccinationCenterRepository;

    @Autowired
    DoctorRepository doctorRepository;


    public AddCenterResponseDto addCenter(AddCenterRequestDto addCenterRequestDto) {
        //request dto to entity
        VaccinationCenter vaccinationCenter = new VaccinationCenter();
        vaccinationCenter.setCenterName(addCenterRequestDto.getCenterName());
        vaccinationCenter.setCenterType(addCenterRequestDto.getCenterType());
        vaccinationCenter.setAddress(addCenterRequestDto.getAddress());

        VaccinationCenter savedVaccinationCenter = vaccinationCenterRepository.save(vaccinationCenter);

        //entity to response dto
        AddCenterResponseDto addCenterResponseDto = new AddCenterResponseDto();
        addCenterResponseDto.setCenterId(savedVaccinationCenter.getId());
        addCenterResponseDto.setMessage("center added successfully");

        return addCenterResponseDto;
    }

    public List<DoctorResponseDto> getAllDoctorOfThisCenter(CenterType centerType) {
        List<Doctor> doctorList = doctorRepository.findAll();
        List<DoctorResponseDto> doctorResponseDtoList = new ArrayList<>();
        for(Doctor doctor:doctorList){
            if(doctor.getCenter().equals(centerType)){
                doctorResponseDtoList.add(new DoctorResponseDto(doctor.getName(),doctor.getId()));
            }
        }
        return doctorResponseDtoList;
    }

    public CenterResponseDto getCenterWithHighestNoOfDoctors() {
        List<VaccinationCenter> vaccinationCenterList = vaccinationCenterRepository.findAll();
        VaccinationCenter ansVaccinationCenter=null;
        int count=0;
        for(VaccinationCenter vaccinationCenter:vaccinationCenterList){
            if(vaccinationCenter.getDoctors().size()>count){
                count=vaccinationCenter.getDoctors().size();
                ansVaccinationCenter=vaccinationCenter;
            }
        }
        CenterResponseDto centerResponseDto = new CenterResponseDto();
        centerResponseDto.setCenterId(ansVaccinationCenter.getId());
        centerResponseDto.setCenterName(ansVaccinationCenter.getCenterName());
        centerResponseDto.setCenterType(ansVaccinationCenter.getCenterType());
        centerResponseDto.setAddress(ansVaccinationCenter.getAddress());

        return centerResponseDto;
    }

    public CenterResponseDto getCenterWithHighestNoOfDoctorsInThisCenterType(CenterType centerType) {
        List<VaccinationCenter> vaccinationCenterList = vaccinationCenterRepository.findAll();
        VaccinationCenter ansVaccinationCenter=null;
        int count=0;
        for(VaccinationCenter vaccinationCenter:vaccinationCenterList){
            if(vaccinationCenter.getCenterType().equals(centerType) && vaccinationCenter.getDoctors().size()>count){
                count=vaccinationCenter.getDoctors().size();
                ansVaccinationCenter=vaccinationCenter;
            }
        }
        CenterResponseDto centerResponseDto = new CenterResponseDto();
        centerResponseDto.setCenterId(ansVaccinationCenter.getId());
        centerResponseDto.setCenterName(ansVaccinationCenter.getCenterName());
        centerResponseDto.setCenterType(ansVaccinationCenter.getCenterType());
        centerResponseDto.setAddress(ansVaccinationCenter.getAddress());

        return centerResponseDto;
    }
}
