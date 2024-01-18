package com.microservice.demo.model;

import com.microservice.demo.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

// El repositorio que permite acceder a la base de datos
public interface TodoRepository extends JpaRepository<Todo, Long> {

}
