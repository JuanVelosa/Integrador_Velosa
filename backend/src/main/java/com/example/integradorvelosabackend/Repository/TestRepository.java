package com.example.integradorvelosabackend.Repository;

import com.example.integradorvelosabackend.Entity.Test;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestRepository extends CrudRepository<Test, Long> {

    public List<Test> findByRegisterId(Long registerId);

    @Query("SELECT u FROM Test u WHERE u.register IS NULL")
    public List<Test> findByRegisterIdIsNull();

}