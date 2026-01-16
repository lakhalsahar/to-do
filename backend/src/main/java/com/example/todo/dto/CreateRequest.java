package com.example.todo.dto;

import com.example.todo.entity.Priority;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
public class CreateRequest {
    @NotBlank
    private String title;
    private String description;
    private Priority priority = Priority.MEDIUM;
    private LocalDate dueDate;

}
