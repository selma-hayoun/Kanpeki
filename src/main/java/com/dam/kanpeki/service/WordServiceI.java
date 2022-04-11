package com.dam.kanpeki.service;

import java.util.List;
import java.util.Optional;

import com.dam.kanpeki.model.Word;

public interface WordServiceI {

	public List<Word> findAllWords();

	public List<Word> findByJapaneseContaining(String jWord);

	public List<Word> findByEnglishContaining(String eWord);

	public List<Word> findBySpanishContaining(String sWord);

	public List<Word> findWordsByMatcher(String wField);

	public List<Word> findByCategoryId(Long id);

	public Optional<Word> findById(Long id);

	public Word addWord(Word w);

	public void removeWordById(Long id);

	public void updateWord(Word w);

}
