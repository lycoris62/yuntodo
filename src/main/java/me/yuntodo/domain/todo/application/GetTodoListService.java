package me.yuntodo.domain.todo.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.yuntodo.domain.todo.dao.TodoRepository;
import me.yuntodo.domain.todo.domain.Todo;
import me.yuntodo.domain.todo.dto.TodoResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class GetTodoListService {

    private final TodoRepository todoRepository;

    public List<TodoResponse> getTodoList(String username, LocalDate localDate) {
        List<Todo> todoList = todoRepository.findAllByUsernameAndCreatedAtBetween(
                username,
                localDate.atTime(LocalTime.MIN),
                localDate.atTime(LocalTime.MAX));

        return todoList.stream().map(TodoResponse::new).toList();
    }
}
