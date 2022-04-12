package com.dam.kanpeki.repository;

import java.util.Date;
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

	@Query(value = "SELECT * FROM results r WHERE r.user_id = :userId", nativeQuery = true)
	List<Result> findResultsUser(Long userId);

	@Query("SELECT new com.dam.kanpeki.model.custom.ResultPerCategoryData(r.categoryId, COUNT(r), AVG(r.score)) "
			+ "FROM Result AS r GROUP BY r.categoryId")
	List<ResultPerCategoryData> resultsPerCategory();

	@Query(value = "SELECT * FROM results r WHERE r.result_date BETWEEN :startDate AND :endDate ORDER BY r.result_date DESC", nativeQuery = true)
	List<Result> findResultsBetweenDates(Date startDate, Date endDate);

}
