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

    Patient findById(long patientId);

    @Query("SELECT u FROM Patient u WHERE u.name LIKE %:name%")
    List<Patient> findByName(@Param("name") String name);

    @Query("SELECT u FROM Patient u WHERE u.email = :email")
    Optional<Patient> findByEmail(@Param("email") String email);

    @Query("SELECT u FROM Patient u WHERE u.documentId = :documentId")
    public Optional<Patient> findByDocumentId(@Param("documentId") String documentId);


    @Query("SELECT p FROM Patient p WHERE p.doctor.id = :doctorId")
    List<Patient> findByDoctorId(@Param("doctorId") long doctorId);


    @Query("SELECT u FROM Patient u WHERE CAST(u.documentId AS string) LIKE CONCAT(:documentId, '%')")
    List<Patient> findByDocumentIdStartingWith(@Param("documentId") String documentId);


    @Query("SELECT u FROM Patient u WHERE u.name LIKE CONCAT(:search, '%') OR u.documentId LIKE CONCAT(:search, '%')  ")
    List<Patient> findUnassignedPatientBySearch(String search);

}
