import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { TodoService } from './service';
import { Todo, Status, Priority, CreateRequest } from './Todo';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App implements OnInit {

  todos: Todo[] = [];

  statuses: Status[] = ['TODO', 'IN_PROGRESS', 'DONE', 'MISSED'];
  priorities: Priority[] = ['LOW', 'MEDIUM', 'HIGH'];

  newTodo: CreateRequest = {
    title: '',
    description: '',
    priority: 'MEDIUM'
  };

  constructor(private todoService: TodoService) {}

  ngOnInit(): void {
    this.loadTodos();
  }

  loadTodos(): void {
    this.todoService.getAll()
      .subscribe(data => this.todos = data);
  }

  addTodo(): void {
    if (!this.newTodo.title?.trim()) return;

    this.todoService.create(this.newTodo)
      .subscribe(() => {
        this.newTodo = { title: '', description: '', priority: 'MEDIUM' };
        this.loadTodos();
      });
  }

  updateTodo(todo: Todo): void {
    this.todoService.update(todo)
      .subscribe(() => this.loadTodos());
  }

  deleteTodo(id: number): void {
    this.todoService.delete(id)
      .subscribe(() => this.loadTodos());
  }
}
