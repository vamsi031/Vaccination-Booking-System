package com.example.vaccinationbookingsystem.service;

import com.example.vaccinationbookingsystem.Dto.RequestDto.BookAppointmentRequestDto;
import com.example.vaccinationbookingsystem.Dto.RespnseDto.AddPersonResponseDto;
import com.example.vaccinationbookingsystem.Dto.RespnseDto.BookAppointmentResponseDto;
import com.example.vaccinationbookingsystem.Dto.RespnseDto.CenterResponseDto;
import com.example.vaccinationbookingsystem.exceptions.DoctorNotFoundException;
import com.example.vaccinationbookingsystem.exceptions.PersonNotFoundException;
import com.example.vaccinationbookingsystem.model.Appointment;
import com.example.vaccinationbookingsystem.model.Doctor;
import com.example.vaccinationbookingsystem.model.Person;
import com.example.vaccinationbookingsystem.model.VaccinationCenter;
import com.example.vaccinationbookingsystem.repository.AppointmentRepository;
import com.example.vaccinationbookingsystem.repository.DoctorRepository;
import com.example.vaccinationbookingsystem.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AppointmentService {
    @Autowired
    AppointmentRepository appointmentRepository;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    JavaMailSender javaMailSender;

    public BookAppointmentResponseDto bookAppointment(BookAppointmentRequestDto bookAppointmentRequestDto) {
        Optional<Person> optionalPerson = personRepository.findById(bookAppointmentRequestDto.getPersonId());
        if(optionalPerson.isEmpty())throw new PersonNotFoundException("person Not Found");
        Optional<Doctor> optionalDoctor = doctorRepository.findById(bookAppointmentRequestDto.getDoctorId());
        if(optionalDoctor.isEmpty())throw new DoctorNotFoundException("doctor not found");
        Person person = optionalPerson.get();
        Doctor doctor = optionalDoctor.get();

        //create appointment obj
        Appointment appointment = new Appointment();
        appointment.setAppointmentId(String.valueOf(UUID.randomUUID()));
        appointment.setPerson(person);
        appointment.setDoctor(doctor);

        Appointment savedAppointment = appointmentRepository.save(appointment);

        //if we add appointment in doctor and person (i.e two times )before saving it in db at that time it doesn't have id
        //thus it will create two rows with same data we need to add after saving it

        doctor.getAppointmentList().add(savedAppointment);//added savedAppointment 
        person.getAppointments().add(savedAppointment);//added savedAppointment

        Person savedPerson = personRepository.save(person);
        Doctor savedDoctor = doctorRepository.save(doctor);
        VaccinationCenter vaccinationCenter = savedDoctor.getCenter();

        //send mail
        SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
        simpleMailMessage.setFrom("vamsiWinner0@gmail.com");
        simpleMailMessage.setTo(person.getEmail());
        simpleMailMessage.setSubject("congrats appointment booked");

        String text = "Congrats!! "+savedPerson.getName()+" Your appointment has been booked with Doctor "+
                savedDoctor.getName() + ". Your vaccination center name is: " + vaccinationCenter.getCenterName() + " Please reach at this address "+
                vaccinationCenter.getAddress() + " at this time: " + savedAppointment.getAppointmentDate()+" Dhanyawad!!!";

        simpleMailMessage.setText(text);

        javaMailSender.send(simpleMailMessage);

        //center to centerResponseDto
        CenterResponseDto centerResponseDto = new CenterResponseDto();
        centerResponseDto.setCenterId(vaccinationCenter.getId());
        centerResponseDto.setCenterName(vaccinationCenter.getCenterName());
        centerResponseDto.setCenterType(vaccinationCenter.getCenterType());
        centerResponseDto.setAddress(vaccinationCenter.getAddress());

        BookAppointmentResponseDto bookAppointmentResponseDto = new BookAppointmentResponseDto();
        bookAppointmentResponseDto.setAppointmentId(appointment.getAppointmentId());
        bookAppointmentResponseDto.setAppointmentDate(appointment.getAppointmentDate());
        bookAppointmentResponseDto.setDoctorName(person.getName());
        bookAppointmentResponseDto.setDoctorName(doctor.getName());
        bookAppointmentResponseDto.setCenterResponseDto(centerResponseDto);

        return bookAppointmentResponseDto;
    }

    public List<BookAppointmentResponseDto> getAllAppointmentsOfADoctor(int doctorId) {

        Optional<Doctor> optionalDoctor = doctorRepository.findById(doctorId);
        if(optionalDoctor.isEmpty())throw new DoctorNotFoundException("doctor not found");
        Doctor doctor = optionalDoctor.get();
        List<Appointment> appointmentList = doctor.getAppointmentList();
        List<BookAppointmentResponseDto> list = new ArrayList<>();
        for(Appointment appointment:appointmentList){
            BookAppointmentResponseDto bookAppointmentResponseDto = new BookAppointmentResponseDto();
            bookAppointmentResponseDto.setAppointmentId(appointment.getAppointmentId());
            bookAppointmentResponseDto.setAppointmentDate(appointment.getAppointmentDate());
            bookAppointmentResponseDto.setPersonName(appointment.getPerson().getName());
            bookAppointmentResponseDto.setDoctorName(appointment.getDoctor().getName());

            list.add(bookAppointmentResponseDto);

        }

        return list;




    }

    public List<BookAppointmentResponseDto> getAllAppointmentsOfAPerson(int personId) {
        Optional<Person> optionalPerson = personRepository.findById(personId);
        if(optionalPerson.isEmpty())throw new PersonNotFoundException("person not found");
        Person person = optionalPerson.get();
        List<Appointment> appointmentList = person.getAppointments();
        List<BookAppointmentResponseDto> list = new ArrayList<>();
        for(Appointment appointment:appointmentList){
            BookAppointmentResponseDto bookAppointmentResponseDto = new BookAppointmentResponseDto();
            bookAppointmentResponseDto.setAppointmentId(appointment.getAppointmentId());
            bookAppointmentResponseDto.setAppointmentDate(appointment.getAppointmentDate());
            bookAppointmentResponseDto.setPersonName(appointment.getPerson().getName());
            bookAppointmentResponseDto.setDoctorName(appointment.getDoctor().getName());

            list.add(bookAppointmentResponseDto);

        }

        return list;
    }
}
