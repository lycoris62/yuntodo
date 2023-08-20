package me.yuntodo.domain.todo.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.yuntodo.domain.todo.dao.TodoRepository;
import me.yuntodo.domain.todo.domain.Todo;
import me.yuntodo.domain.todo.dto.AddTodoRequest;
import me.yuntodo.domain.todo.dto.TodoResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AddTodoService {

    private final TodoRepository todoRepository;

    @PreAuthorize("#username == authentication.principal.username")
    public TodoResponse addTodo(AddTodoRequest request, String username) {
        Todo todo = new Todo(request.getContent(), username);
        Todo savedTodo = todoRepository.save(todo);
        return new TodoResponse(savedTodo);
    }
}
