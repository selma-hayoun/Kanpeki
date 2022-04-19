package com.dam.kanpeki.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dam.kanpeki.exception.DataNotFoundException;
import com.dam.kanpeki.model.Word;
import com.dam.kanpeki.model.dto.RequestWordDTO;
import com.dam.kanpeki.model.dto.ResponseWordDTO;
import com.dam.kanpeki.model.dto.mapper.WordDTOMapperStruct;
import com.dam.kanpeki.repository.WordRepository;
import com.dam.kanpeki.service.FileSystemStorageServiceI;
import com.dam.kanpeki.service.WordServiceI;
import com.dam.kanpeki.utils.KanpekiConstants;

@Service
public class WordServiceImpl implements WordServiceI {

	@Autowired
	private WordRepository wRepo;

	@Autowired
	private WordDTOMapperStruct mapper;

	@Autowired
	private FileSystemStorageServiceI storeService;

	@Override
	public List<ResponseWordDTO> findAllWords() {
		return mapper.toWordDTOList(wRepo.findAll().stream());
	}

	@Override
	public List<ResponseWordDTO> findWordsByMatcher(String wField) {
		Word w = new Word();
		w.setJapanese(wField);
		w.setEnglish(wField);
		w.setSpanish(wField);

		ExampleMatcher customExMatcher = ExampleMatcher.matchingAny()
				.withMatcher(KanpekiConstants.WORD_JAPANESE_NAME,
						ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
				.withMatcher(KanpekiConstants.WORD_ENGLISH_NAME,
						ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
				.withMatcher(KanpekiConstants.WORD_SPANISH_NAME,
						ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());

		Example<Word> wordExample = Example.of(w, customExMatcher);

		return mapper.toWordDTOList(wRepo.findAll(wordExample).stream());
	}

	@Override
	public List<ResponseWordDTO> findByCategoryId(Long id) {
		return mapper.toWordDTOList(wRepo.findByCategoryId(id).stream());
	}

	@Override
	public Optional<ResponseWordDTO> findById(Long id) {
		Optional<Word> opWord = wRepo.findById(id);

		if (!opWord.isPresent()) {
			throw new DataNotFoundException(KanpekiConstants.EMPTY_STRING);
		} else {
			return Optional.of(mapper.toWordDTO(opWord.get()));
		}
	}

	@Override
	public ResponseWordDTO addWord(RequestWordDTO w, MultipartFile file) {
		Word wTemp = mapper.requestWordDTOtoWord(w);
		wTemp.setUrlImage(storeService.saveFileRequest(file));
		return (mapper.toWordDTO(wRepo.save(wTemp)));
	}

	@Override
	public void removeWordById(Long id) {
		Optional<Word> opWord = wRepo.findById(id);

		if (!opWord.isPresent()) {
			throw new DataNotFoundException(KanpekiConstants.EMPTY_STRING);
		} else {
			// Eliminamos la imagen del almacenamiento
			storeService.delete(opWord.get().getUrlImage());
			wRepo.deleteById(id);
		}
	}

	@Override
	public ResponseWordDTO updateWord(RequestWordDTO w, MultipartFile file, Long id) {
		Word mappedW = mapper.requestWordDTOtoWord(w);

		mappedW.setUrlImage(storeService.saveFileRequest(file));

		Word mappedWUpdated = wRepo.findById(id).map(newW -> {
			newW.setJapanese(mappedW.getJapanese());
			newW.setEnglish(mappedW.getEnglish());
			newW.setSpanish(mappedW.getSpanish());
			newW.setFurigana(mappedW.getFurigana());
			newW.setUrlImage(mappedW.getUrlImage());
			newW.setCategoryId(mappedW.getCategoryId());
			wRepo.save(newW);
			return newW;
		}).orElseThrow(() -> new DataNotFoundException(KanpekiConstants.EMPTY_STRING));

		return mapper.toWordDTO(mappedWUpdated);
	}

}
