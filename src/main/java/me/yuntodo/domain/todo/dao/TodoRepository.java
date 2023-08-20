package me.yuntodo.domain.todo.dao;

import me.yuntodo.domain.todo.domain.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.access.prepost.PostFilter;

import java.time.LocalDateTime;
import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {

    @PostFilter("filterObject.username == authentication.name")
    List<Todo> findAllByUsernameAndCreatedAtBetween(String username, LocalDateTime start, LocalDateTime end);

    @Query("SELECT t FROM Todo t WHERE t.username=?#{authentication.name}")
    List<Todo> findAllByUsername(String username);
}
