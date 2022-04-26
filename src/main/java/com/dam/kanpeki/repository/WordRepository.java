package com.dam.kanpeki.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dam.kanpeki.model.Word;

@Repository
public interface WordRepository extends JpaRepository<Word, Long> {

	List<Word> findByCategoryId(Long id);

	@Query(value = "SELECT COUNT(*) FROM words w WHERE w.japanese = :japanese", nativeQuery = true)
	Integer countWordJapaneseUnique(@Param("japanese") String japanese);

	Optional<Word> findByJapanese(String japanese);

}
