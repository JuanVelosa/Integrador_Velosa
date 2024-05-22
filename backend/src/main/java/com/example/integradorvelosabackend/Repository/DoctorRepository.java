package com.example.integradorvelosabackend.Repository;

import java.util.List;

import com.example.integradorvelosabackend.Entity.Doctor;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

@Transactional
public interface DoctorRepository extends CrudRepository<Doctor, Long> {

    @Query("SELECT u FROM Doctor u WHERE u.documentId = :document")
    public Optional<Doctor> findByDocumentId(@Param("document") String document);

    @Query("SELECT u FROM Doctor u WHERE u.email = :email")
    public Optional<Doctor> searchByEmail(@Param("email") String email);

    @Query("SELECT u FROM Doctor u WHERE LOWER(u.name) = LOWER(:name) OR LOWER(u.lastname) = LOWER(:name) OR LOWER(CONCAT(u.name, ' ', u.lastname)) = LOWER(:name)")
    public List<Doctor> findByName(@Param("name") String name);

    @Query("SELECT u FROM Doctor u WHERE u.email = :email AND u.password = :password")
    public Optional<Doctor> getUserByEmailAndPassword(
            @Param("email") String email,
            @Param("password") String password
    );

    @Modifying
    @Query("UPDATE Doctor d SET d.name = :name, d.lastname = :lastname, d.password = :password , d.email = :email, d.documentId = :documentId WHERE d.id = :id")
    public void updateById(
            @Param("id") long id,
            @Param("name") String name,
            @Param("lastname") String lastname,
            @Param("email") String email,
            @Param("password") String password,
            @Param("documentId") String documentId

    );



}
