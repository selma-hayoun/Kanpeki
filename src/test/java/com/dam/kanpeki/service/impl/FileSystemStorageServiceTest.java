package com.dam.kanpeki.service.impl;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;

import com.dam.kanpeki.exception.StorageFileNotFoundException;
import com.dam.kanpeki.util.KanpekiTestsConstants;

class FileSystemStorageServiceTest {

	private Path rootLocation;
	private FileSystemStorageServiceImpl fileSystemStorageService;
	private File dummyFile;

	@BeforeEach
	public void setup() {
		fileSystemStorageService = new FileSystemStorageServiceImpl();
		rootLocation = Paths.get("src", "test", "resources", "upload-dir-test");
		fileSystemStorageService.rootLocation = rootLocation;
		dummyFile = new File(rootLocation.toString(), KanpekiTestsConstants.FILE_NAME_EXAMPLE);
	}

	@Test
	@DisplayName("Test Should Pass When init Then Should Ok")
	void whenCallingInit_givenCorrectRootLocation_thenShouldThrowNothing() throws Exception {

		assertDoesNotThrow(fileSystemStorageService::init);
	}

	@Test
	@DisplayName("Test Should Pass When saveFileRequest is Given Correct File Returns Not Null")
	void whenCallingSaveFileRequest_givenCorrectFile_thenShouldReturnPath() throws Exception {

		dummyFile = new File(rootLocation.getFileName().toString(), KanpekiTestsConstants.FILE_NAME_EXAMPLE);

		byte[] dummyFileContent = null;
		try {
			dummyFileContent = Files.readAllBytes(Paths.get(dummyFile.getPath()));
		} catch (final IOException e) {
		}

		assertNotNull(
				fileSystemStorageService.saveFileRequest(new MockMultipartFile(dummyFile.getName(), dummyFileContent)));

	}

	@Test
	@DisplayName("Test Should Pass When saveFileRequest is Given Incorrect fileName Throws IllegalArgumentException")
	void whenCallingSaveFileRequest_givenNullFileName_thenShouldThrowIllegalArgumentException() throws Exception {

		FileInputStream input = new FileInputStream(dummyFile);

		assertThrows(IllegalArgumentException.class, () -> {
			fileSystemStorageService.saveFileRequest(new MockMultipartFile(null, input));
		});

	}

	@Test
	@DisplayName("Test Should Pass When load is Given Correct File Name Returns NotNull")
	void whenCallingLoad_givenCorrectFileName_thenShouldReturnNotNull() throws Exception {
		assertNotNull(fileSystemStorageService.load(dummyFile.getAbsolutePath()));
	}

	@Test
	@DisplayName("Test Should Pass When load is Given Correct File Name Returns Correct Path")
	void whenCallingLoad_givenCorrectFileName_thenShouldReturnCorrectPath() throws Exception {
		assertEquals(fileSystemStorageService.load(dummyFile.getName()).toString(),
				"src" + File.separator + "test" + File.separator + "resources" + File.separator + "upload-dir-test"
						+ File.separator + KanpekiTestsConstants.FILE_NAME_EXAMPLE);
	}

	@Test
	@DisplayName("Test Should Pass When loadAsResource is Given Correct File Name Returns Not Null")
	void whenCallingLoadAsResource_givenCorrectFileName_thenShouldReturnNotNull() throws Exception {

		assertNotNull(fileSystemStorageService.loadAsResource(dummyFile.getName()));
	}

	@Test
	@DisplayName("Test Should Pass When loadAsResource is Given Incorrect File Name Throws StorageFileNotFoundException")
	void whenCallingLoadAsResource_givenIncorrectFileName_thenShouldThrowStorageFileNotFoundException()
			throws Exception {

		assertThrows(StorageFileNotFoundException.class, () -> {
			fileSystemStorageService.loadAsResource(KanpekiTestsConstants.FILE_NAME_NOT_EXISTS_EXAMPLE);
		});
	}

}
