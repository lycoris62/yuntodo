package me.yuntodo.domain.todo.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.yuntodo.domain.model.BaseEntity;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Todo extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "todo_id")
    private Long id;

    @Column(name = "content", nullable = false, length = 50)
    private String content;

    @Column(name = "is_done", nullable = false)
    private Boolean isDone;

    @Column(name = "username", nullable = false, length = 20)
    private String username;

    public Todo(String content, String username) {
        this.content = content;
        this.isDone = false;
        this.username = username;
    }

    public void setContent(String newContent) {
        this.content = newContent;
    }

    public void setIsDone(boolean updatedIsDone) {
        this.isDone = updatedIsDone;
    }
}
