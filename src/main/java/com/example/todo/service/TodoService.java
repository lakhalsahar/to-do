package com.example.todo.service;

import com.example.todo.dto.CreateRequest;
import com.example.todo.dto.ProgressResponse;
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
    private void updateMissedStatus(Todo todo) {
        if (todo.getDueDate() != null
                && todo.getStatus() != Status.DONE
                && todo.getDueDate().isBefore(LocalDate.now())) {

            todo.setStatus(Status.MISSED);
        }
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
        List<Todo> todos = repo.findAll();

        boolean changed = false;
        for (Todo todo : todos) {
            Status before = todo.getStatus();
            updateMissedStatus(todo);
            if (before != todo.getStatus()) {
                changed = true;
            }
        }
        if (changed) {
            repo.saveAll(todos);
        }
        return todos;
    }

    public Todo findById(Long id) {
        Todo todo = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo not found: " + id));
        Status before = todo.getStatus();
        updateMissedStatus(todo);
        if (before != todo.getStatus()) {
            todo = repo.save(todo);
        }
        return todo;
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
    public ProgressResponse getProgress() {
        long total = repo.count();
        if (total == 0) {
            return new ProgressResponse(0, 0, 0);
        }

        long done = repo.findAll().stream()
                .filter(t -> t.getStatus() == Status.DONE)
                .count();

        int percentage = (int) ((done * 100) / total);
        return new ProgressResponse(total, done, percentage);
    }

}
