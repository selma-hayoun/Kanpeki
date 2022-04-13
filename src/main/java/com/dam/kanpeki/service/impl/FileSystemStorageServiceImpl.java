package com.dam.kanpeki.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.dam.kanpeki.exception.StorageException;
import com.dam.kanpeki.exception.StorageFileNotFoundException;
import com.dam.kanpeki.service.FileSystemStorageServiceI;

@Service
public class FileSystemStorageServiceImpl implements FileSystemStorageServiceI {

	@Value("${upload.root-location}")
	private Path rootLocation;

	/**
	 * Inicializa el almacenamiento del proyecto
	 */
	@Override
	public void init() {
		try {
			Files.createDirectories(rootLocation);
		} catch (IOException e) {
			throw new StorageException("Could not initialize storage", e);
		}
	}

	/**
	 * Método que almacena un fichero en el almacenamiento desde un objeto de tipo
	 * {@link org.springframework.web.multipart#MultipartFile} MultipartFile
	 */
	@Override
	public String store(MultipartFile file) {

		String filename = StringUtils.cleanPath(file.getOriginalFilename());
		String extension = StringUtils.getFilenameExtension(filename);
		String justFilename = filename.replace("." + extension, "");
		String storedFilename = System.currentTimeMillis() + "_" + justFilename + "." + extension;
		try {
			if (file.isEmpty()) {
				throw new StorageException("Failed to store empty file " + filename);
			}
			if (filename.contains("..")) {
				// Security check
				throw new StorageException(
						"Cannot store file with relative path outside current directory " + filename);
			}
			try (InputStream inputStream = file.getInputStream()) {
				Files.copy(inputStream, this.rootLocation.resolve(storedFilename), StandardCopyOption.REPLACE_EXISTING);
				return storedFilename;
			}
		} catch (IOException e) {
			throw new StorageException("Failed to store file " + filename, e);
		}
	}

	/**
	 * Devuelve la ruta de todos los ficheros que hay en el almacenamiento
	 */
	@Override
	public Stream<Path> loadAll() {
		try {
			return Files.walk(this.rootLocation, 1).filter(path -> !path.equals(this.rootLocation))
					.map(this.rootLocation::relativize);
		} catch (IOException e) {
			throw new StorageException("Failed to read stored files", e);
		}
	}

	/**
	 * Devuelve un fichero a partir de su nombre
	 */
	@Override
	public Path load(String filename) {
		return rootLocation.resolve(filename);
	}

	/**
	 * Carga un fichero a partir de su nombre
	 */
	@Override
	public Resource loadAsResource(String filename) {
		try {
			Path file = load(filename);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new StorageFileNotFoundException("Could not read file: " + filename);

			}
		} catch (MalformedURLException e) {
			throw new StorageFileNotFoundException("Could not read file: " + filename, e);
		}
	}

	/**
	 * Elimina un fichero del almacenamiento a partir de su nombre
	 */
	@Override
	public void delete(String filename) {

		if (filename != null && !filename.isEmpty()) {
			String justFilename = StringUtils.getFilename(filename);
			try {
				Path file = load(justFilename);
				Files.deleteIfExists(file);
			} catch (IOException e) {
				throw new StorageException("Error deleting a file", e);
			}
		}

	}

	/**
	 * Elimina todos los ficheros almacenados
	 */
	@Override
	public void deleteAll() {
		FileSystemUtils.deleteRecursively(rootLocation.toFile());
	}

}
