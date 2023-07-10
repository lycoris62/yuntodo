package me.yuntodo.domain.todo.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.yuntodo.domain.todo.dao.TodoRepository;
import me.yuntodo.domain.todo.domain.Todo;
import me.yuntodo.domain.todo.dto.CheckTodoRequest;
import me.yuntodo.domain.todo.dto.CheckTodoResponse;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CheckTodoService {

    private final TodoRepository todoRepository;

    public CheckTodoResponse checkTodo(CheckTodoRequest request, String username) throws IllegalAccessException {
        Todo todo = todoRepository.findById(request.getId())
                .orElseThrow(() -> new IllegalArgumentException("알 수 없는 투두"));

        UsernameValidateUtil.validateUsername(username, todo);

        todo.setIsDone(request.getIsChecked());
        todoRepository.save(todo);

        return new CheckTodoResponse(todo.getIsDone());
    }
}
