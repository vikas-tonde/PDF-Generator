package com.example.DynamicPDFGenerator.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.DynamicPDFGenerator.models.PdfGenerationRequest;
import com.example.DynamicPDFGenerator.services.PdfGeneratorService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class PdfController {
	private PdfGeneratorService service;

	@PostMapping("/generatepdf")
	public ResponseEntity<Resource> generatePDF(@RequestBody PdfGenerationRequest content) {
		String path = service.createPdf(content);
		File file = new File(path);
		FileInputStream fl = null;
		byte[] byteData = new byte[(int) file.length()];
		try {
			fl = new FileInputStream(file);
			fl.read(byteData);
			fl.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(MediaType.APPLICATION_PDF_VALUE))
				.header("attachment; filename=\"" + path + "\"").body(new ByteArrayResource(byteData));
	}
}
