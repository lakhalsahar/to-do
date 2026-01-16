# To-Do Application

A full-stack task management application built with Spring Boot and Angular.

## Overview

This project is a complete CRUD application that allows users to create, manage, and track their tasks. It features a REST API backend with a modern single-page application frontend.

## Tech Stack

### Backend
- **Framework**: Spring Boot 4.0.1
- **Language**: Java 17
- **Build Tool**: Maven
- **Database**: H2 (in-memory)
- **ORM**: Spring Data JPA / Hibernate

### Frontend
- **Framework**: Angular 20
- **Language**: TypeScript 5.9
- **Build Tool**: Angular CLI

## Features

- Create, read, update, and delete tasks
- Task properties include title, description, priority, status, and due date
- Automatic status update to "MISSED" for overdue incomplete tasks
- Progress tracking with completion statistics
- Responsive user interface

## Project Structure

```
to-do/
├── backend/                 # Spring Boot REST API
│   ├── src/main/java/com/example/todo/
│   │   ├── config/          # CORS configuration
│   │   ├── controller/      # REST endpoints
│   │   ├── service/         # Business logic
│   │   ├── entity/          # JPA entities and enums
│   │   ├── dto/             # Request/Response objects
│   │   ├── repository/      # Data access layer
│   │   └── exception/       # Exception handling
│   └── pom.xml
│
└── todo-ui/                 # Angular frontend
    ├── src/app/
    │   ├── app.ts           # Root component
    │   ├── service.ts       # HTTP service
    │   └── Todo.ts          # TypeScript interfaces
    └── package.json
```

## API Endpoints

| Method | Endpoint           | Description          |
|--------|--------------------|----------------------|
| GET    | `/todos`           | Get all todos        |
| GET    | `/todos/{id}`      | Get todo by ID       |
| POST   | `/todos`           | Create new todo      |
| PUT    | `/todos/{id}`      | Update existing todo |
| DELETE | `/todos/{id}`      | Delete todo          |
| GET    | `/todos/progress`  | Get progress stats   |

## Data Model

### Todo Entity

| Field       | Type     | Description                              |
|-------------|----------|------------------------------------------|
| id          | Long     | Auto-generated primary key               |
| title       | String   | Task title (required, max 255 chars)     |
| description | String   | Task description (optional, max 2000)    |
| status      | Enum     | TODO, IN_PROGRESS, DONE, MISSED          |
| priority    | Enum     | LOW, MEDIUM, HIGH (default: MEDIUM)      |
| dueDate     | Date     | Optional due date                        |

## Getting Started

### Prerequisites

- Java 17 or higher
- Node.js 18 or higher
- npm

### Running the Backend

```bash
cd backend
./mvnw spring-boot:run
```

The API will be available at `http://localhost:8080`.

The H2 database console can be accessed at `http://localhost:8080/h2-console` with:
- JDBC URL: `jdbc:h2:mem:todo-db`
- Username: `sa`
- Password: (empty)

### Running the Frontend

```bash
cd todo-ui
npm install
npm start
```

The application will be available at `http://localhost:4200`.

## Configuration

### Backend (application.properties)

- Server port: 8080
- Database: H2 in-memory (data is not persisted between restarts)
- CORS: Configured to allow requests from `http://localhost:4200`

### Frontend

The API base URL is configured in `src/app/service.ts`.

## Notes

- This project uses an in-memory H2 database, so all data is lost when the backend server restarts
- CORS is configured for local development; adjust for production deployment