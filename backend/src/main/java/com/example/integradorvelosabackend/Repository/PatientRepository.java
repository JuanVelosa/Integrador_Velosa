package com.example.integradorvelosabackend.Repository;

import com.example.integradorvelosabackend.Entity.Patient;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;


@Transactional
public interface PatientRepository extends CrudRepository<Patient, Long> {


}
