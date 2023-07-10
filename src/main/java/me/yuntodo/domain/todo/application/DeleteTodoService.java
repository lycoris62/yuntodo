package me.yuntodo.domain.todo.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.yuntodo.domain.todo.dao.TodoRepository;
import me.yuntodo.domain.todo.domain.Todo;
import me.yuntodo.domain.todo.dto.DeleteTodoRequest;
import me.yuntodo.global.common.response.SuccessResponse;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeleteTodoService {

    private final TodoRepository todoRepository;

    public SuccessResponse deleteTodo(DeleteTodoRequest request, String username) throws IllegalAccessException {

        Todo todo = todoRepository.findById(request.getId())
                .orElseThrow(() -> new IllegalArgumentException("잘못된 아이디"));
        UsernameValidateUtil.validateUsername(username, todo);

        todoRepository.deleteById(request.getId());

        return new SuccessResponse();
    }
}
