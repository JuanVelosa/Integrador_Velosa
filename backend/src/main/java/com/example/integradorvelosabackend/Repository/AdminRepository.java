package com.example.integradorvelosabackend.Repository;

import com.example.integradorvelosabackend.Entity.Admin;
import com.example.integradorvelosabackend.Entity.Doctor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AdminRepository extends CrudRepository<Admin, Long> {

    @Query("SELECT u FROM Admin u WHERE u.email = :email")
    public Optional<Admin> searchByEmail(@Param("email") String email);

    @Query("SELECT u FROM Admin u WHERE u.email = :email AND u.password = :password")
    public Optional<Admin> getUserByEmailAndPassword(
            @Param("email") String email,
            @Param("password") String password
    );
}

