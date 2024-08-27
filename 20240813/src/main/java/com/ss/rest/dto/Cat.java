package com.ss.rest.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Cat {
	private int id;
	private String name;
	private String info;
	private String eyeColor;
	private String hairLength;
	private String features;
	private String color;
	private String weight;
	private Date createDate;
}
