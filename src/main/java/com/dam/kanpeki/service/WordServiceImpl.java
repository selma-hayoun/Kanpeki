package com.dam.kanpeki.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import com.dam.kanpeki.model.Word;
import com.dam.kanpeki.repository.WordRepository;

@Service
public class WordServiceImpl implements WordServiceI {

	@Autowired
	private WordRepository wRepo;

	@Override
	public List<Word> findAllWords() {
		return wRepo.findAll();
	}

	@Override
	public List<Word> findByJapaneseContaining(String jWord) {
		return wRepo.findByJapaneseContaining(jWord);
	}

	@Override
	public List<Word> findByEnglishContaining(String eWord) {
		return wRepo.findByEnglishContaining(eWord);
	}

	@Override
	public List<Word> findBySpanishContaining(String sWord) {
		return wRepo.findBySpanishContaining(sWord);
	}

	@Override
	public List<Word> findWordsByMatcher(String wField) {
		Word w = new Word();
		w.setJapanese(wField);
		w.setEnglish(wField);
		w.setSpanish(wField);

		ExampleMatcher customExMatcher = ExampleMatcher.matchingAny()
				.withMatcher("japanese", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
				.withMatcher("english", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
				.withMatcher("spanish", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());

		Example<Word> wordExample = Example.of(w, customExMatcher);

		return wRepo.findAll(wordExample);
	}

	@Override
	public List<Word> findByCategoryId(Long id) {
		return wRepo.findByCategoryId(id);
	}

	@Override
	public Optional<Word> findById(Long id) {
		return wRepo.findById(id);
	}

	@Override
	public Word addWord(Word w) {
		return wRepo.save(w);
	}

	@Override
	public void removeWordById(Long id) {
		wRepo.deleteById(id);
	}

	@Override
	public void updateWord(Word w) {
		wRepo.save(w);
	}

}
