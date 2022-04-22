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
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.dam.kanpeki.controller.FilesController;
import com.dam.kanpeki.exception.StorageException;
import com.dam.kanpeki.exception.StorageFileNotFoundException;
import com.dam.kanpeki.service.FileSystemStorageServiceI;
import com.dam.kanpeki.utils.KanpekiConstants;

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
			throw new StorageException(KanpekiConstants.EX_STORAGE_EXCEPTION_NOT_INIT, e);
		}
	}

	/**
	 * Método que almacena un fichero en el almacenamiento desde un objeto de tipo
	 * {@link org.springframework.web.multipart#MultipartFile} MultipartFile
	 */
	@Override
	public String saveFileRequest(MultipartFile file) {

		String urlImg = KanpekiConstants.EMPTY_STRING;

		if (file != null && !file.isEmpty()) {
			// Almacenamos el fichero y obtenemos su URL
			String img;

			String originalFileName = file.getOriginalFilename();

			String filename = KanpekiConstants.EMPTY_STRING;

			if (originalFileName != null) {
				filename = StringUtils.cleanPath(originalFileName);
			}

			String extension = StringUtils.getFilenameExtension(filename);
			String justFilename = filename.replace(KanpekiConstants.DOT_STRING + extension,
					KanpekiConstants.EMPTY_STRING);
			String storedFilename = System.currentTimeMillis() + KanpekiConstants.UNDERSCORE_STRING + justFilename
					+ KanpekiConstants.DOT_STRING + extension;
			try {
				if (file.isEmpty()) {
					throw new StorageException(KanpekiConstants.EX_STORAGE_EXCEPTION_EMPTY_FILE + filename);
				}
				if (filename.contains(KanpekiConstants.DOUBLE_DOTS_STRING)) {
					// Security check
					throw new StorageException(KanpekiConstants.EX_STORAGE_EXCEPTION_SECURITY_CHECK + filename);
				}
				try (InputStream inputStream = file.getInputStream()) {
					Files.copy(inputStream, this.rootLocation.resolve(storedFilename),
							StandardCopyOption.REPLACE_EXISTING);
					img = storedFilename;
				}
			} catch (IOException e) {
				throw new StorageException(KanpekiConstants.EX_STORAGE_EXCEPTION_IOEX_STORE + filename, e);
			}

			urlImg = MvcUriComponentsBuilder
					.fromMethodName(FilesController.class, KanpekiConstants.FILES_SERVE_FILE, img, null).build()
					.toUriString();
		}

		return urlImg;
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
			throw new StorageException(KanpekiConstants.EX_STORAGE_EXCEPTION_IOEX_READ, e);
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
				throw new StorageFileNotFoundException(KanpekiConstants.EX_STORAGE_FILE_NOT_FOUND_READ + filename);

			}
		} catch (MalformedURLException e) {
			throw new StorageFileNotFoundException(KanpekiConstants.EX_STORAGE_FILE_NOT_FOUND_READ + filename, e);
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
				throw new StorageException(KanpekiConstants.EX_STORAGE_EXCEPTION_IOEX_DELETE, e);
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
