package com.dam.kanpeki.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.dam.kanpeki.model.Result;
import com.dam.kanpeki.model.ResultId;
import com.dam.kanpeki.model.custom.ResultPerCategoryData;

public interface ResultServiceI {

	public List<Result> findAllResults();

	public List<Result> findByCategoryId(Long id);

	public List<Result> findResultsUser(Long userId);

	public List<ResultPerCategoryData> resultsPerCategory();

	public Optional<Result> findById(ResultId id);

	public List<Result> findResultsBetweenDates(Date startDate, Date endDate);

	public Result addResult(Result r);

	public void removeResultById(ResultId id);

	public void removeResult(Result r);

	public void updateResult(Result r);

	public void deleteResultsByUserId(Long id);

}
