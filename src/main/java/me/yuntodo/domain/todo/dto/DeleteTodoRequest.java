package me.yuntodo.domain.todo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeleteTodoRequest {

    private Long id;

    public DeleteTodoRequest(@JsonProperty("id") Long id) {
        this.id = id;
    }
}
