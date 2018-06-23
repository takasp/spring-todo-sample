package com.example.demo.api.todo;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;

public class TodoResource implements Serializable {

    private static final long serialVersionUID = 1L;

    private String todoId;

    @Size(min = 1, max = 30)
    private String todoTitle;

    private boolean finished;

    private LocalDateTime createdAt;

    public String getTodoId() {
        return todoId;
    }

    public void setTodoId(String todoId) {
        this.todoId = todoId;
    }

    public String getTodoTitle() {
        return todoTitle;
    }

    public void setTodoTitle(String todoTitle) {
        this.todoTitle = todoTitle;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
