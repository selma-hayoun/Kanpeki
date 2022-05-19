package com.dam.kanpeki.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.dam.kanpeki.model.Result;
import com.dam.kanpeki.model.ResultId;
import com.dam.kanpeki.model.custom.ResultPerCategoryData;
import com.dam.kanpeki.model.dto.RequestResultDTO;
import com.dam.kanpeki.model.dto.ResponseResultDTO;

public interface ResultServiceI {

	public List<ResponseResultDTO> findAllResults();

	public List<ResponseResultDTO> findResultsUser(Long userId);

	public List<ResultPerCategoryData> findResultsUserPerCategory(Long userId);

	public List<ResultPerCategoryData> resultsPerCategory();

	public Optional<Result> findById(ResultId id);

	public List<ResponseResultDTO> findResultsBetweenDates(Date startDate, Date endDate);

	public ResponseResultDTO addResult(RequestResultDTO r);

	public void removeResultById(ResponseResultDTO r);

	public void removeResult(ResponseResultDTO r);

	public void updateResult(Result r);

	public void deleteResultsByUserId(Long id);

}
