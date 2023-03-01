package com.example.DynamicPDFGenerator.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import com.example.DynamicPDFGenerator.models.PdfGenerationRequest;
import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.html2pdf.css.apply.ICssApplierFactory;
import com.itextpdf.html2pdf.css.apply.impl.DefaultCssApplierFactory;
import com.itextpdf.html2pdf.resolver.font.DefaultFontProvider;
import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.pdf.PdfWriter;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PdfGeneratorService {
	private SpringTemplateEngine springTemplateEngine;
	private Environment environment;

	public String createPdf(PdfGenerationRequest content) {
		Context context = new Context();
		context.setVariable("content", content);
		String html = springTemplateEngine.process("template", context);
		String filePath = null;
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		try {
			PdfWriter writer = new PdfWriter(byteArrayOutputStream);
			DefaultFontProvider defaultFontProvider = new DefaultFontProvider(false, true, false);
			ConverterProperties converterProperties = new ConverterProperties();
			ICssApplierFactory instance = DefaultCssApplierFactory.getInstance();
			converterProperties.setCssApplierFactory(instance);
			converterProperties.setFontProvider(defaultFontProvider);
			HtmlConverter.convertToPdf(html, writer, converterProperties);
			filePath = environment.getProperty("pdf.files.storage-path") + File.separator + System.currentTimeMillis()
					+ ".pdf";
			FileOutputStream fout = new FileOutputStream(filePath);
			byteArrayOutputStream.writeTo(fout);
			byteArrayOutputStream.close();
			byteArrayOutputStream.flush();
			fout.close();
			fout.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return filePath;

	}
}
