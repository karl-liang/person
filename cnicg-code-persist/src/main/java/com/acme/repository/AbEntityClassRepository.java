package com.acme.repository;

import com.example.entity.AbEntityClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
* Generated by Spring Data Generator on 10/08/2018
*/
@Repository
public interface AbEntityClassRepository extends JpaRepository<AbEntityClass, Integer>, JpaSpecificationExecutor<AbEntityClass> {

}
