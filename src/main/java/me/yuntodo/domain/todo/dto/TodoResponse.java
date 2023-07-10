package me.yuntodo.domain.todo.dto;

import lombok.Getter;
import lombok.Setter;
import me.yuntodo.domain.todo.domain.Todo;

import java.time.LocalDateTime;

@Getter
@Setter
public class TodoResponse {

    private Long id;
    private String content;
    private LocalDateTime createdAt;
    private boolean isDone;

    public TodoResponse(Todo todo) {
        this.id = todo.getId();
        this.content = todo.getContent();
        this.createdAt = todo.getCreatedAt();
        this.isDone = todo.getIsDone();
    }
}
