package com.dam.kanpeki.repository;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dam.kanpeki.model.Result;
import com.dam.kanpeki.model.ResultId;
import com.dam.kanpeki.model.custom.ResultPerCategoryData;

@Repository
public interface ResultRepository extends JpaRepository<Result, ResultId> {

	@Query(value = "SELECT * FROM results r WHERE r.user_id = :userId ORDER BY r.result_date DESC", nativeQuery = true)
	List<Result> findResultsUser(Long userId);

	@Query("SELECT new com.dam.kanpeki.model.custom.ResultPerCategoryData(r.categoryId, COUNT(*), AVG(r.score)) "
			+ "FROM Result AS r WHERE r.id.userId = :userId GROUP BY r.categoryId")
	List<ResultPerCategoryData> findResultsUserPerCategory(Long userId);

	@Query("SELECT new com.dam.kanpeki.model.custom.ResultPerCategoryData(r.categoryId, COUNT(r), AVG(r.score)) "
			+ "FROM Result AS r GROUP BY r.categoryId")
	List<ResultPerCategoryData> resultsPerCategory();

	@Query(value = "SELECT * FROM results r WHERE r.result_date BETWEEN :startDate AND :endDate ORDER BY r.result_date DESC", nativeQuery = true)
	List<Result> findResultsBetweenDates(Date startDate, Date endDate);

	@Modifying
	@Query(value = "DELETE FROM results WHERE user_id = :id", nativeQuery = true)
	@Transactional
	void deleteResultsByUserId(@Param("id") Long id);

}
