package TodoList.API.TodoListApi.Service;

import TodoList.API.TodoListApi.Entity.TodoEntity;
import TodoList.API.TodoListApi.Entity.UserEntity;
import TodoList.API.TodoListApi.Model.TodoBean;
import TodoList.API.TodoListApi.Repository.TodoRepository;
import TodoList.API.TodoListApi.Repository.UserRepository;
import TodoList.API.TodoListApi.Utils.GenericResponse;
import org.springframework.stereotype.Service;

@Service
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;
    private final UserRepository userRepository;

    public TodoServiceImpl(TodoRepository todoRepository, UserRepository userRepository) {
        this.todoRepository = todoRepository;
        this.userRepository = userRepository;
    }

    @Override
    public GenericResponse createTodo(TodoBean todoBean, String email) {
        try {

            UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

            TodoEntity todoEntity = new TodoEntity();
            todoEntity.setTitle(todoBean.getTitle());
            todoEntity.setDescription(todoBean.getDescription());
            todoEntity.setUser(user);

            todoRepository.save(todoEntity);

            return new GenericResponse(0, "Task created successfully", todoBean);
        } catch (Exception e) {
            return new GenericResponse(2, "Error creating task: " + e.getMessage(), null);
        }
    }
}