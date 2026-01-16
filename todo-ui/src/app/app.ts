import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { TodoService } from './service';
import { Todo, Status, Priority, CreateRequest, ProgressResponse } from './Todo';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App implements OnInit {
  todos: Todo[] = [];

  progress: ProgressResponse | null = null;

  statuses: Status[] = ['TODO', 'IN_PROGRESS', 'DONE', 'MISSED'];
  priorities: Priority[] = ['LOW', 'MEDIUM', 'HIGH'];

  newTodo: CreateRequest = {
    title: '',
    description: '',
    priority: 'MEDIUM',
    dueDate: ''
  };

  constructor(private todoService: TodoService) {}

  ngOnInit(): void {
    this.refresh();
  }

  refresh(): void {
    this.loadTodos();
    this.loadProgress();
  }

  loadTodos(): void {
    this.todoService.getAll().subscribe(data => (this.todos = data));
  }

  loadProgress(): void {
    this.todoService.getProgress().subscribe(p => (this.progress = p));
  }

  addTodo(): void {
    if (!this.newTodo.title?.trim()) return;

    const payload: CreateRequest = {
      title: this.newTodo.title.trim(),
      description: this.newTodo.description?.trim() || undefined,
      priority: this.newTodo.priority || 'MEDIUM',
      dueDate: this.newTodo.dueDate || undefined
    };

    this.todoService.create(payload).subscribe(() => {
      this.newTodo = { title: '', description: '', priority: 'MEDIUM', dueDate: '' };
      this.refresh();
    });
  }

  updateTodo(todo: Todo): void {
    this.todoService.update(todo).subscribe(() => this.refresh());
  }

  deleteTodo(id: number): void {
    this.todoService.delete(id).subscribe(() => this.refresh());
  }
}
