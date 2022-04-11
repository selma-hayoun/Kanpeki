package com.dam.kanpeki.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dam.kanpeki.model.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

	List<Question> findByCategoryId(Long id);

	List<Question> findByStatementContaining(String qSate);
}
