package me.yuntodo.domain.todo.application;

import me.yuntodo.domain.todo.domain.Todo;

public class UsernameValidateUtil {

    public static void validateUsername(String username, Todo todo) throws IllegalAccessException {
        if (!todo.getUsername().equals(username)) {
            throw new IllegalAccessException("다른 유저의 투두");
        }
    }
}
