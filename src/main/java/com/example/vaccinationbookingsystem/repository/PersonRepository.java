package com.example.vaccinationbookingsystem.repository;

import com.example.vaccinationbookingsystem.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person,Integer> {
    Person findByEmail(String oldEmail);
}
