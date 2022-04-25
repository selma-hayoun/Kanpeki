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

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class FilesController {

	private final FileSystemStorageServiceI storageService;

	@ApiOperation(value = "serveFile", notes = "Get a file from the server by file name")
	@ApiResponses(value = { @ApiResponse(code = 200, message = KanpekiConstants.CONTROLLER_MSG_200),
			@ApiResponse(code = 400, message = KanpekiConstants.CONTROLLER_MSG_400),
			@ApiResponse(code = 401, message = KanpekiConstants.CONTROLLER_MSG_401),
			@ApiResponse(code = 403, message = KanpekiConstants.CONTROLLER_MSG_403),
			@ApiResponse(code = 404, message = KanpekiConstants.CONTROLLER_MSG_404),
			@ApiResponse(code = 500, message = KanpekiConstants.CONTROLLER_MSG_500) })
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
