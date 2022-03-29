package com.example.demo.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;

@Data
@Document
@Builder
public class Todo {
	@Id
	private String id;
	private String title;
	private String description;
	private String imagePath;
	private String imageFileName;

}
