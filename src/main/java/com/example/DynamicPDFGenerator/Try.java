package com.example.DynamicPDFGenerator;

import java.io.File;
import java.io.FileNotFoundException;

import org.springframework.util.ResourceUtils;

public class Try {

	public static void main(String[] args) {
//		System.out.println(System.getProperty("user.home"));
		try {
			File file = new File("E:\\Assignment\\1677408138221.pdf");
			System.out.println(file.isFile());
//			ResourceUtils.
			System.out.println(file.getAbsolutePath());
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}

}
