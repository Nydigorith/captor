package com.example.file_validation;

import com.example.file_validation.controller.FileController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FileValidationApplication {


	@Autowired
	FileController fileController;

	public static void main(String[] args) {
		SpringApplication.run(FileValidationApplication.class, args);
	}

}
