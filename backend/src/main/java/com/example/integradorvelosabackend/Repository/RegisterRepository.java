package com.example.integradorvelosabackend.Repository;

import com.example.integradorvelosabackend.Entity.Register;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface RegisterRepository  extends CrudRepository<Register, Long> {

    @Query("SELECT u FROM Register u WHERE u.author = :name")
    public Optional<Register> searchByName(@Param("name")String name);

    @Query("SELECT u FROM Register u WHERE u.patient = :documentId")
    public Optional<Register> searchByPatient(@Param("documentId") long documentId);

    @Query("SELECT u FROM Register u WHERE u.author = :name")
    List<Register> searchByNameList(@Param("name")String name);

}
