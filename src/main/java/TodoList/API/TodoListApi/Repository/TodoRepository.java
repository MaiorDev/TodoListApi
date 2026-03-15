package TodoList.API.TodoListApi.Repository;

import TodoList.API.TodoListApi.Entity.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface TodoRepository extends JpaRepository<TodoEntity, Long> {
    // Si lo anterior falla, usa el nombre exacto de la relación
    List<TodoEntity> findByUser_Id(Long id);
}