package me.yuntodo.domain.todo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class AddTodoRequest {

    @Length(max = 50)
    private String content;

    public AddTodoRequest(@JsonProperty("content") String content) {
        this.content = content;
    }
}
