package me.yuntodo.domain.todo.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.yuntodo.domain.todo.application.*;
import me.yuntodo.domain.todo.dto.*;
import me.yuntodo.global.common.response.SuccessResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/todo")
public class TodoController {

    private final AddTodoService addTodoService;
    private final EditTodoService editTodoService;
    private final GetTodoListService getTodoListService;
    private final DeleteTodoService deleteTodoService;
    private final CheckTodoService checkTodoService;

    @GetMapping("/{date}")
    public ResponseEntity<List<TodoResponse>> getTodolist(
            @PathVariable String date,
            Authentication authentication) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate localDate = LocalDate.parse(date, formatter);

        List<TodoResponse> todoList = getTodoListService.getTodoList(authentication.getName(), localDate);
        return ResponseEntity.ok(todoList);
    }

    @PostMapping("/add")
    public ResponseEntity<TodoResponse> addTodo(
            @RequestBody @Valid AddTodoRequest addTodoRequest,
            Authentication authentication) {

        TodoResponse todoResponse = addTodoService.addTodo(addTodoRequest, authentication.getName());
        return ResponseEntity.ok(todoResponse);
    }

    @PatchMapping("/edit")
    public ResponseEntity<SuccessResponse> editTodo(
            @RequestBody @Valid EditTodoRequest request,
            Authentication authentication) throws IllegalAccessException {

        SuccessResponse successResponse = editTodoService.editTodo(request, authentication.getName());
        return ResponseEntity.ok(successResponse);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<SuccessResponse> deleteTodo(
            @RequestBody DeleteTodoRequest request,
            Authentication authentication) throws IllegalAccessException {

        SuccessResponse successResponse = deleteTodoService.deleteTodo(request, authentication.getName());
        return ResponseEntity.ok(successResponse);
    }

    @PatchMapping("/check")
    public ResponseEntity<CheckTodoResponse> checkTodo(
            @RequestBody CheckTodoRequest request,
            Authentication authentication) throws IllegalAccessException {

        CheckTodoResponse checkTodoResponse = checkTodoService.checkTodo(request, authentication.getName());
        return ResponseEntity.ok(checkTodoResponse);
    }
}
