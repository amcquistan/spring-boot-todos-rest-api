package com.thecodinginterface.todobackend.controllers;

import com.thecodinginterface.todobackend.models.Todo;
import com.thecodinginterface.todobackend.repositories.TodoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/todos")
public class TodoController {

    @Autowired
    private TodoRepository todoRepository;

    @GetMapping
    public List<Todo> list() {
        return todoRepository.findAll();
    }

    @GetMapping()
    @RequestMapping("{id}")
    public Todo get(@PathVariable Long id) {
        return todoRepository.getOne(id);
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public Todo create(@RequestBody final Todo todo) {
        return todoRepository.saveAndFlush(todo);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public Todo update(@PathVariable Long id, @RequestBody Todo todo) {
        var existingTodo = todoRepository.getOne(id);
        BeanUtils.copyProperties(todo, existingTodo, "id");
        return todoRepository.saveAndFlush(todo);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        todoRepository.deleteById(id);
    }
}
