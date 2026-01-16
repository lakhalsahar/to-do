package com.example.todo.service;

import com.example.todo.dto.CreateRequest;
import com.example.todo.dto.UpdateRequest;
import com.example.todo.entity.Status;
import com.example.todo.entity.Todo;
import com.example.todo.exception.ResourceNotFoundException;
import com.example.todo.repository.TodoRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TodoService {
    private final TodoRepo repo;

    public TodoService(TodoRepo repo) {
        this.repo = repo;
    }

    public Todo create(CreateRequest req) {
        Todo todo = new Todo();
        todo.setTitle(req.getTitle());
        todo.setDescription(req.getDescription());
        todo.setPriority(req.getPriority());
        todo.setDueDate(req.getDueDate());
        return repo.save(todo);
    }
    public List<Todo> findAll() {
        return repo.findAll();
    }
    public Todo findById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo not found: " + id));
    }

    public Todo update(Long id, UpdateRequest req) {
        Todo todo = findById(id);
        todo.setTitle(req.getTitle());
        todo.setDescription(req.getDescription());
        todo.setStatus(req.getStatus());
        todo.setPriority(req.getPriority());
        todo.setDueDate(req.getDueDate());
        return repo.save(todo);
    }
    public void delete(Long id) {
        if (!repo.existsById(id)) {
            throw new ResourceNotFoundException("Todo not found: " + id);
        }
        repo.deleteById(id);
    }
}
