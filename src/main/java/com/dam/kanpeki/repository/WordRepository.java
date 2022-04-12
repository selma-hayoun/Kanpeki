package com.dam.kanpeki.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dam.kanpeki.model.Word;

@Repository
public interface WordRepository extends JpaRepository<Word, Long> {

	List<Word> findByCategoryId(Long id);

}