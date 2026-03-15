package TodoList.API.TodoListApi.Entity;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "todos")
@Data
public class TodoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    @ManyToOne
    private UserEntity user;

}
