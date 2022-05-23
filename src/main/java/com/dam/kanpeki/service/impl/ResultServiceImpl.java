package com.dam.kanpeki.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.dam.kanpeki.exception.DataNotFoundException;
import com.dam.kanpeki.exception.InvalidFKReferencesException;
import com.dam.kanpeki.model.Result;
import com.dam.kanpeki.model.ResultId;
import com.dam.kanpeki.model.custom.ResultPerCategoryData;
import com.dam.kanpeki.model.dto.RequestResultDTO;
import com.dam.kanpeki.model.dto.ResponseCategoryDTO;
import com.dam.kanpeki.model.dto.ResponseResultDTO;
import com.dam.kanpeki.model.dto.mapper.ResultDTOMapperStruct;
import com.dam.kanpeki.repository.ResultRepository;
import com.dam.kanpeki.service.CategoryServiceI;
import com.dam.kanpeki.service.ResultServiceI;
import com.dam.kanpeki.utils.KanpekiConstants;

@Service
public class ResultServiceImpl implements ResultServiceI {

	@Autowired
	protected ResultRepository rRepo;

	@Autowired
	protected CategoryServiceI catService;

	@Autowired
	protected ResultDTOMapperStruct mapper;

	@Override
	public List<ResponseResultDTO> findAllResults() {
		return mapper.toResultDTOList(rRepo.findAll().stream());
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
		ResponseCategoryDTO cat = null;
		// Verificamos si el id de usuario y de categoría existen con los try-catch
		try {
			cat = catService.findById(r.getCategoryId()).orElseThrow(
					() -> new InvalidFKReferencesException(KanpekiConstants.INVALID_REFERENCES_RESULT_EX_CATEGORY_ID));
		} catch (DataNotFoundException ex) {
			throw new InvalidFKReferencesException(KanpekiConstants.INVALID_REFERENCES_RESULT_EX_CATEGORY_ID);
		}

		// Verficamos que sea una categoría de preguntas
		if (Boolean.FALSE.equals(cat.getIsQuestion())) {
			throw new InvalidFKReferencesException(
					KanpekiConstants.INVALID_REFERENCES_RESULT_EX_CATEGORY_NOT_IS_QUESTION);
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
		ResponseCategoryDTO cat = null;
		// Verificamos si el id de usuario y de categoría existen
		try {
			cat = catService.findById(r.getCategoryId()).orElseThrow(
					() -> new InvalidFKReferencesException(KanpekiConstants.INVALID_REFERENCES_RESULT_EX_CATEGORY_ID));
		} catch (DataNotFoundException ex) {
			// Así tomamos el DataNotFoundException del findById de categoría y lo
			// transformamos en nuestra excepción personalizada
			throw new InvalidFKReferencesException(KanpekiConstants.INVALID_REFERENCES_RESULT_EX_CATEGORY_ID);
		}

		// Verficamos que sea una categoría de preguntas
		if (Boolean.FALSE.equals(cat.getIsQuestion())) {
			throw new InvalidFKReferencesException(
					KanpekiConstants.INVALID_REFERENCES_RESULT_EX_CATEGORY_NOT_IS_QUESTION);
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
		// Limitamos a los 20 últimos resultados
		return mapper.toResultDTOList(rRepo.findResultsUser(userId).stream()).stream()
				.limit(KanpekiConstants.USER_RESULTS_PER_REQUEST_LIMIT).collect(Collectors.toList());
	}

	@Override
	public void deleteResultsByUserId(Long id) {
		rRepo.deleteResultsByUserId(id);
	}

	@Override
	public List<ResultPerCategoryData> findResultsUserPerCategory(Long userId) {
		return rRepo.findResultsUserPerCategory(userId);
	}

}
