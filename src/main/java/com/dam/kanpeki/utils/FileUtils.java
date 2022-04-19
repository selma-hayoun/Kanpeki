package com.dam.kanpeki.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.dam.kanpeki.controller.FilesController;
import com.dam.kanpeki.service.FileSystemStorageServiceI;

@Component
public class FileUtils {

	private static FileSystemStorageServiceI storeService;

	@Autowired
	private FileUtils(FileSystemStorageServiceI sService) {
		FileUtils.storeService = sService;
	}

	public static String saveFileRequest(MultipartFile file) {

		String urlImg = KanpekiConstants.EMPTY_STRING;

		if (file != null && !file.isEmpty()) {
			// Almacenamos el fichero y obtenemos su URL
			String img = storeService.store(file);
			urlImg = MvcUriComponentsBuilder.fromMethodName(FilesController.class, KanpekiConstants.FILES_SERVE_FILE, img, null).build()
					.toUriString();
		}

		return urlImg;
	}

}
