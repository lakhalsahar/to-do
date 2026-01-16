package com.example.todo.controller;

import com.example.todo.service.TodoService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TodoController {
    private final TodoService todoService;
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

}
