package me.yuntodo.domain.todo.dto;

import lombok.Getter;
import lombok.Setter;

import java.beans.ConstructorProperties;

@Getter
@Setter
public class CheckTodoRequest {

    private Long id;
    private Boolean isChecked;

    @ConstructorProperties({"id", "isChecked"})
    public CheckTodoRequest(Long id, Boolean isChecked) {
        this.id = id;
        this.isChecked = isChecked;
    }
}
