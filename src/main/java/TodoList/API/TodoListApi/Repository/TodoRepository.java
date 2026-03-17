package TodoList.API.TodoListApi.Repository;

import TodoList.API.TodoListApi.Entity.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TodoRepository extends JpaRepository<TodoEntity, Long> {
    List<TodoEntity> findByUserId(Long userId);
}