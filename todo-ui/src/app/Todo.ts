export type Status = 'TODO' | 'IN_PROGRESS' | 'DONE' | 'MISSED';
export type Priority = 'LOW' | 'MEDIUM' | 'HIGH';

export interface Todo {
  id: number;
  title: string;
  description?: string;
  status: Status;
  priority: Priority;
  dueDate?: string;
}

export interface CreateRequest {
  title: string;
  description?: string;
  priority?: Priority;
  dueDate?: string;
}

export interface UpdateRequest {
  title?: string;
  description?: string;
  status?: Status;
  priority?: Priority;
  dueDate?: string;
}

export interface ProgressResponse {
  total: number;
  done: number;
  percentage: number;
}
