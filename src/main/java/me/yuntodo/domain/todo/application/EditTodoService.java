package me.yuntodo.domain.todo.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.yuntodo.domain.todo.dao.TodoRepository;
import me.yuntodo.domain.todo.domain.Todo;
import me.yuntodo.domain.todo.dto.EditTodoRequest;
import me.yuntodo.global.common.response.SuccessResponse;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EditTodoService {

    private final TodoRepository todoRepository;

    public SuccessResponse editTodo(EditTodoRequest request, String username) throws IllegalAccessException {
        Todo todo = todoRepository.findById(request.getId())
                .orElseThrow(() -> new IllegalArgumentException("없는 투두"));

        UsernameValidateUtil.validateUsername(username, todo);

        todo.setContent(request.getNewContent());
        todoRepository.save(todo);

        return new SuccessResponse();
    }
}
