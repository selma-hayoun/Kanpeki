package com.dam.kanpeki.service;

import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.dam.kanpeki.model.dto.RequestWordDTO;
import com.dam.kanpeki.model.dto.ResponseWordDTO;

public interface WordServiceI {

	public List<ResponseWordDTO> findAllWords();

	public List<ResponseWordDTO> findWordsByMatcher(String wField);

	public List<ResponseWordDTO> findByCategoryId(Long id);

	public Optional<ResponseWordDTO> findById(Long id);

	public ResponseWordDTO addWord(RequestWordDTO w, MultipartFile file);

	public void removeWordById(Long id);

	public ResponseWordDTO updateWord(RequestWordDTO w, MultipartFile file, Long id);

}
