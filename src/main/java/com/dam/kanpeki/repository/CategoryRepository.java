package com.dam.kanpeki.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dam.kanpeki.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

	List<Category> findByIsQuestionTrue();

	List<Category> findByIsQuestionFalse();

	@Query(value = "SELECT COUNT(*) FROM categories cat WHERE cat.unit_name = :unitName AND cat.category_name = :categoryName", nativeQuery = true)
	Integer countCategoriesUnique(@Param("unitName") String unitName, @Param("categoryName") String categoryName);

	Optional<Category> findByUnitNameAndCategoryName(String unitName, String categoryName);

}
