package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.domain.Todo;
import com.example.demo.service.TodoService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/s3/example")
@AllArgsConstructor
@CrossOrigin("*")
public class TodoController {
	
	TodoService service;
	
	@GetMapping
	public ResponseEntity<List<Todo>> getTodos() {
		return new ResponseEntity<List<Todo>>(service.getAllTodos(), HttpStatus.OK);
	}
	
	@PostMapping(path = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Todo> saveTodo(@RequestParam("title") String title, @RequestParam("description") String description, @RequestParam("file") MultipartFile file) {
		return new ResponseEntity<Todo>(service.saveTodo(title, description, file), HttpStatus.OK);
	}
	
	@GetMapping("/{id}/image/download")
	public byte[] downloadTodoImage(@PathVariable("id") String id) {
		return service.downloadTodoImage(id);
	}

}
