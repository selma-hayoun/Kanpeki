package com.dam.kanpeki.service.impl;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeEach;

public class FileSystemStorageServiceTest {

	private Path rootLocation;

	@BeforeEach
	public void setup() {
		rootLocation = Paths.get("./upload-dir");

	}

}
