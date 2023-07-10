package me.yuntodo.domain.todo.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.beans.ConstructorProperties;

@Getter
@Setter
public class EditTodoRequest {

    private Long id;

    @Length(max = 50)
    private String newContent;

    @ConstructorProperties({"id", "newContent"})
    public EditTodoRequest(Long id, String newContent) {
        this.id = id;
        this.newContent = newContent;
    }
}
