package com.thecodinginterface.todobackend.repositories;

import com.thecodinginterface.todobackend.models.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
}
