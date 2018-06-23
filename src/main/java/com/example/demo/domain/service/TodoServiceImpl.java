package com.example.demo.domain.service;

import com.example.demo.api.error.BusinessException;
import com.example.demo.api.error.ResourceNotFoundException;
import com.example.demo.domain.model.Todo;
import com.example.demo.infrastructure.datasource.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.UUID;

@Service
@Transactional
public class TodoServiceImpl implements TodoService {

    private static final long MAX_UNFINISHED_COUNT = 5;

    private final TodoRepository todoRepository;

    @Autowired
    public TodoServiceImpl(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Todo findOne(String todoId) {
        Todo todo = todoRepository.findOne(todoId);
        if (todo == null) {
            throw new ResourceNotFoundException("[E404] The requested Todo is not found. (id="
                    + todoId + ")");
        }
        return todo;
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Todo> findAll() {
        return todoRepository.findAll();
    }

    @Override
    public Todo create(Todo todo) {
        long unfinishedCount = todoRepository.countByFinished(false);
        if (unfinishedCount >= MAX_UNFINISHED_COUNT) {
            throw new BusinessException("[E001] The count of un-finished Todo must not be over "
                    + MAX_UNFINISHED_COUNT + ".");
        }

        String todoId = UUID.randomUUID().toString();
        LocalDateTime createdAt = LocalDateTime.now();

        todo.setTodoId(todoId);
        todo.setCreatedAt(createdAt);
        todo.setFinished(false);

        todoRepository.create(todo);

        return todo;
    }

    @Override
    public Todo finish(String todoId) {
        Todo todo = findOne(todoId);
        if (todo.isFinished()) {
            throw new BusinessException("[E002] The requested Todo is already finished. (id="
                    + todoId + ")");
        }
        todo.setFinished(true);
        todoRepository.update(todo);
        return todo;
    }

    @Override
    public void delete(String todoId) {
        Todo todo = findOne(todoId);
        todoRepository.delete(todo);
    }
}
