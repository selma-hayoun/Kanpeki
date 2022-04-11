package com.dam.kanpeki.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dam.kanpeki.model.Result;
import com.dam.kanpeki.model.ResultId;
import com.dam.kanpeki.model.custom.ResultPerCategoryData;
import com.dam.kanpeki.repository.ResultRepository;
import com.dam.kanpeki.service.ResultServiceI;

@Service
public class ResultServiceImpl implements ResultServiceI {

	@Autowired
	private ResultRepository rRepo;

	@Override
	public List<Result> findAllResults() {
		return rRepo.findAll();
	}

	@Override
	public List<Result> findByCategoryId(Long id) {
		return rRepo.findByCategoryId(id);
	}

	@Override
	public List<ResultPerCategoryData> resultsPerCategory() {
		return rRepo.resultsPerCategory();
	}

	@Override
	public Optional<Result> findById(ResultId id) {
		return rRepo.findById(id);
	}

	@Override
	public Result addResult(Result r) {
		return rRepo.save(r);
	}

	@Override
	public void removeResultById(ResultId id) {
		rRepo.deleteById(id);
	}

	@Override
	public void updateResult(Result r) {
		rRepo.save(r);
	}

	@Override
	public List<Result> findByUserId(Long id) {
		return rRepo.findByUserId(id);
	}

}
