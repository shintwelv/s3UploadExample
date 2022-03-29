package com.example.demo.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.domain.Todo;

public interface TodoService {
	
	Todo saveTodo(String title, String description, MultipartFile file);
	
	List<Todo> getAllTodos();

	byte[] downloadTodoImage(String id);

}
