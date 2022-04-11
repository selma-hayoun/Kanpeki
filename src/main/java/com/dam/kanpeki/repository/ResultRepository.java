package com.dam.kanpeki.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dam.kanpeki.model.Result;
import com.dam.kanpeki.model.ResultId;

@Repository
public interface ResultRepository extends JpaRepository<Result, ResultId> {

}
