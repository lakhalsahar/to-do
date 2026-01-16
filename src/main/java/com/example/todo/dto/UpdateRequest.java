package com.example.todo.dto;

import com.example.todo.entity.Priority;
import com.example.todo.entity.Status;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UpdateRequest {
    @NotBlank
    private String title;
    private String description;
    private Status status = Status.TODO;
    private Priority priority = Priority.MEDIUM;
    private LocalDate dueDate;
}
