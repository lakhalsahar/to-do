package com.example.todo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
@Getter
@AllArgsConstructor
public class ProgressResponse {
    private long total;
    private long done;
    private int percentage;

}
