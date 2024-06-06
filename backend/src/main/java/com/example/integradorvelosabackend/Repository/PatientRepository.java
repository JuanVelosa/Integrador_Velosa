package com.example.integradorvelosabackend.Repository;

import com.example.integradorvelosabackend.Entity.Patient;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


@Transactional
public interface PatientRepository extends CrudRepository<Patient, Long> {

    public Optional<Patient> findById(long patientId);

    @Query("SELECT u FROM Patient u WHERE u.name LIKE %:name%")
    public List<Patient> findByName(@Param("name") String name);

    @Query("SELECT u FROM Patient u WHERE u.documentId = :documentId")
    public Optional<Patient> findByDocumentId(@Param("documentId") String documentId);

    @Query("SELECT p FROM Patient p WHERE p.doctor.id = :doctorId")
    public List<Patient> findByDoctorId(@Param("doctorId") long doctorId);


    @Query("SELECT u FROM Patient u WHERE CAST(u.documentId AS string) LIKE CONCAT(:documentId, '%')")
    public List<Patient> findByDocumentIdStartingWith(@Param("documentId") String documentId);


    @Query("SELECT u FROM Patient u WHERE u.name LIKE CONCAT(:search, '%') OR u.documentId LIKE CONCAT(:search, '%')  ")
    public List<Patient> findUnassignedPatientBySearch(String search);

}
