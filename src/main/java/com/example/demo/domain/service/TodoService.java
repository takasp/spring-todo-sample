package com.example.demo.domain.service;

import com.example.demo.domain.model.Todo;

import java.util.Collection;

public interface TodoService {
    Collection<Todo> findAll();

    Todo findOne(String todoId);

    Todo create(Todo todo);

    Todo finish(String todoId);

    void delete(String todoId);
}
