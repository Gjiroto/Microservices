package com.microservice.demo.controller;

import com.microservice.demo.Todo;
import com.microservice.demo.model.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

// El controlador que expone el API REST
@RestController
@RequestMapping("/todos")
public class TodoController {

    @Autowired
    private TodoRepository todoRepository;

    // Obtener todas las tareas
    @GetMapping
    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    // Obtener una tarea por su id
    @GetMapping("/{id}")
    public ResponseEntity<Todo> getTodoById(@PathVariable Long id) {
        Optional<Todo> todo = todoRepository.findById(id);
        if (todo.isPresent()) {
            return ResponseEntity.ok(todo.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Crear una nueva tarea
    @PostMapping
    public ResponseEntity<Todo> createTodo(@RequestBody Todo todo) {
        Todo savedTodo = todoRepository.save(todo);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTodo);
    }

    // Actualizar una tarea existente
    @PutMapping("/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable Long id, @RequestBody Todo todo) {
        Optional<Todo> existingTodo = todoRepository.findById(id);
        if (existingTodo.isPresent()) {
            Todo updatedTodo = existingTodo.get();
            updatedTodo.setTitle(todo.getTitle());
            updatedTodo.setCompleted(todo.isCompleted());
            todoRepository.save(updatedTodo);
            return ResponseEntity.ok(updatedTodo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar una tarea por su id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
        todoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}