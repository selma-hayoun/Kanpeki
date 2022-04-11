package com.dam.kanpeki.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dam.kanpeki.model.Answer;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {

}
