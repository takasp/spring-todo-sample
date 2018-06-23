package com.example.demo.api.todo;

import com.example.demo.domain.model.Todo;
import com.example.demo.domain.service.TodoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/todos")
public class TodoRestController {

    private final TodoService todoService;
    private final ModelMapper modelMapper;

    @Autowired
    public TodoRestController(TodoService todoService, ModelMapper modelMapper) {
        this.todoService = todoService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TodoResource> getTodos() {
        Collection<Todo> todos = todoService.findAll();
        List<TodoResource> todoResources = new ArrayList<>();
        for (Todo todo : todos) {
            todoResources.add(modelMapper.map(todo, TodoResource.class));
        }
        return todoResources;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TodoResource postTodos(@RequestBody @Validated TodoResource todoResource) {
        Todo createdTodo = todoService.create(modelMapper.map(todoResource, Todo.class));
        TodoResource createdTodoResponse = modelMapper.map(createdTodo, TodoResource.class);
        return createdTodoResponse;
    }

    @GetMapping("{todoId}")
    @ResponseStatus(HttpStatus.OK)
    public TodoResource getTodo(@PathVariable("todoId") String todoId) {
        Todo todo = todoService.findOne(todoId);
        TodoResource todoResource = modelMapper.map(todo, TodoResource.class);
        return todoResource;
    }

    @PutMapping("{todoId}")
    @ResponseStatus(HttpStatus.OK)
    public TodoResource putTodo(@PathVariable("todoId") String todoId) {
        Todo finishedTodo = todoService.finish(todoId);
        TodoResource finishedTodoResource = modelMapper.map(finishedTodo, TodoResource.class);
        return finishedTodoResource;
    }

    @DeleteMapping("{todoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTodo(@PathVariable("todoId") String todoId) {
        todoService.delete(todoId);
    }
}
