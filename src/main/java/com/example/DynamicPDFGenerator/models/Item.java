package com.example.DynamicPDFGenerator.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Item {
	private String name;
	private String quantity;
	private double rate;
	private double amount;
}
