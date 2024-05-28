package com.example.integradorvelosabackend.Repository;

import com.example.integradorvelosabackend.Entity.Test;

import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestRepository extends CrudRepository<Test, Long> {





}