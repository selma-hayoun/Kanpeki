package com.dam.kanpeki.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dam.kanpeki.model.Word;

@Repository
public interface WordRepository extends JpaRepository<Word, Long> {

	List<Word> findByJapaneseContaining(String jWord);

	List<Word> findByEnglishContaining(String eWord);

	List<Word> findBySpanishContaining(String sWord);

	List<Word> findByCategoryId(Long id);

}
