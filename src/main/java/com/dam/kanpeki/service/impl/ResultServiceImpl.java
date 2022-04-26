package com.dam.kanpeki.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.dam.kanpeki.exception.DataNotFoundException;
import com.dam.kanpeki.exception.InvalidFKReferencesException;
import com.dam.kanpeki.model.Result;
import com.dam.kanpeki.model.ResultId;
import com.dam.kanpeki.model.custom.ResultPerCategoryData;
import com.dam.kanpeki.model.dto.RequestResultDTO;
import com.dam.kanpeki.model.dto.ResponseResultDTO;
import com.dam.kanpeki.model.dto.mapper.ResultDTOMapperStruct;
import com.dam.kanpeki.repository.ResultRepository;
import com.dam.kanpeki.service.CategoryServiceI;
import com.dam.kanpeki.service.ResultServiceI;
import com.dam.kanpeki.utils.KanpekiConstants;

@Service
public class ResultServiceImpl implements ResultServiceI {

	@Autowired
	private ResultRepository rRepo;

	@Autowired
	private CategoryServiceI catService;

	@Autowired
	private ResultDTOMapperStruct mapper;

	@Override
	public List<ResponseResultDTO> findAllResults() {
		return mapper.toResultDTOList(rRepo.findAll().stream());
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
	public ResponseResultDTO addResult(RequestResultDTO r) {
		// Verificamos si el id de usuario y de categoría existen con los try-catch
		try {
			catService.findById(r.getCategoryId());
		} catch (DataNotFoundException ex) {
			throw new InvalidFKReferencesException(KanpekiConstants.INVALID_REFERENCES_RESULT_EX_CATEGORY_ID);
		}

		try {
			return mapper.toResultDTO(rRepo.save(mapper.requestResultDTOtoResult(r)));
		} catch (DataIntegrityViolationException ex) {
			throw new InvalidFKReferencesException(KanpekiConstants.INVALID_REFERENCES_RESULT_EX_USER_ID);
		}

	}

	@Override
	public void removeResultById(ResponseResultDTO r) {
		ResultId resId = new ResultId(r.getUserId(), r.getResultDate());
		Optional<Result> opResult = rRepo.findById(resId);

		if (!opResult.isPresent()) {
			throw new DataNotFoundException(KanpekiConstants.EMPTY_STRING);
		} else {
			rRepo.deleteById(resId);
		}

	}

	@Override
	public void updateResult(Result r) {
		// Verificamos si el id de usuario y de categoría existen

		try {
			catService.findById(r.getCategoryId());
		} catch (DataNotFoundException ex) {
			// Así tomamos el DataNotFoundException del findById de categoría y lo
			// transformamos en nuestra excepción personalizada
			throw new InvalidFKReferencesException(KanpekiConstants.INVALID_REFERENCES_RESULT_EX_CATEGORY_ID);
		}

		try {
			rRepo.save(r);
		} catch (DataIntegrityViolationException ex) {
			throw new InvalidFKReferencesException(KanpekiConstants.INVALID_REFERENCES_RESULT_EX_USER_ID);
		}

	}

	@Override
	public void removeResult(ResponseResultDTO r) {
		rRepo.delete(mapper.responseResultDTOtoResult(r));
	}

	@Override
	public List<ResponseResultDTO> findResultsBetweenDates(Date startDate, Date endDate) {
		return mapper.toResultDTOList(rRepo.findResultsBetweenDates(startDate, endDate).stream());
	}

	@Override
	public List<ResponseResultDTO> findResultsUser(Long userId) {
		return mapper.toResultDTOList(rRepo.findResultsUser(userId).stream());
	}

	@Override
	public void deleteResultsByUserId(Long id) {
		rRepo.deleteResultsByUserId(id);
	}

}
