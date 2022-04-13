package com.dam.kanpeki.service;

import java.nio.file.Path;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileSystemStorageServiceI {

	public void init();

	public String store(MultipartFile file);

	public Stream<Path> loadAll();

	public Path load(String filename);

	public Resource loadAsResource(String filename);

	public void delete(String filename);

	public void deleteAll();

}
