package com.example.todo.service;

import com.example.todo.repository.TodoRepo;
import org.springframework.stereotype.Service;

@Service
public class TodoService {
    private final TodoRepo repo;

    public TodoService(TodoRepo repo) {
        this.repo = repo;
    }
}
