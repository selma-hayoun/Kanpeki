package com.dam.kanpeki.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dam.kanpeki.model.Result;
import com.dam.kanpeki.model.ResultId;
import com.dam.kanpeki.model.custom.ResultPerCategoryData;

@Repository
public interface ResultRepository extends JpaRepository<Result, ResultId> {

	List<Result> findByCategoryId(Long id);

	List<Result> findByUserId(Long id);

	@Query(value = "SELECT new com.dam.kanpeki.model.custom.ResultPerCategoryData(r.category_id, COUNT(r.score), AVG(r.score)) "
			+ "FROM results AS r GROUP BY r.category_id", nativeQuery = true)
	List<ResultPerCategoryData> resultsPerCategory();

}
