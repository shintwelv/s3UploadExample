package com.example.demo.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.config.BucketName;
import com.example.demo.domain.Todo;
import com.example.demo.repository.TodoRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TodoServiceImpl implements TodoService {

	private final FileStore fileStore;
	private final TodoRepository repository;

	@Override
	public Todo saveTodo(String title, String description, MultipartFile file) {
		if (file.isEmpty()) {
			throw new IllegalStateException("Cannot upload empty file");
		}

		Map<String, String> medadata = new HashMap<String, String>();
		medadata.put("Content-Type", file.getContentType());
		medadata.put("Content-Length", String.valueOf(file.getSize()));

		String path = BucketName.TODO_IMAGE.getBucketName();
		String fileName = file.getOriginalFilename();

		try {
			fileStore.upload(path, fileName, Optional.of(medadata), file.getInputStream());
		} catch (IOException e) {
			throw new IllegalStateException("Cannot upload empty file");
		}

		Todo todo = Todo.builder().description(description).title(title).imagePath(path).imageFileName(fileName)
				.build();
		Todo savedTodo = repository.save(todo);

		return savedTodo;
	}

	@Override
	public byte[] downloadTodoImage(String id) {
		Todo todo = repository.findById(id).get();
		return fileStore.downLoad(todo.getImagePath(), todo.getImageFileName()); 
	}

	@Override
	public List<Todo> getAllTodos() {
		List<Todo> todos = new ArrayList<Todo>();
		repository.findAll().forEach(todos::add);
		return todos;
	}

}
