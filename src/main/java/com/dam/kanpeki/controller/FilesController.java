package com.dam.kanpeki.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dam.kanpeki.service.FileSystemStorageServiceI;
import com.dam.kanpeki.utils.KanpekiConstants;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class FilesController {

	private final FileSystemStorageServiceI storageService;

	@GetMapping(value = "kanpeki/files/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> serveFile(@PathVariable String filename, HttpServletRequest request) {
		Resource file = storageService.loadAsResource(filename);

		String contentType = null;
		try {
			contentType = request.getServletContext().getMimeType(file.getFile().getAbsolutePath());
		} catch (IOException ex) {
			log.info(KanpekiConstants.FILES_CONTROLLER_INFO_FILE_TYPE);
		}

		if (contentType == null) {
			contentType = KanpekiConstants.FILES_CONTENT_TYPE;
		}

		return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType)).body(file);
	}
}
