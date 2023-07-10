package me.yuntodo.domain.todo.dao;

import me.yuntodo.domain.todo.domain.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findAllByUsername(String username);
}
