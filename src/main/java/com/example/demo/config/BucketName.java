package com.example.demo.config;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum BucketName {
	TODO_IMAGE("s3uploadexampleelvin");
	private final String bucketName;

}
