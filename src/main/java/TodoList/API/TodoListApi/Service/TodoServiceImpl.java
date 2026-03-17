package TodoList.API.TodoListApi.Service;

import TodoList.API.TodoListApi.Entity.TodoEntity;
import TodoList.API.TodoListApi.Entity.UserEntity;
import TodoList.API.TodoListApi.Model.TodoBean;
import TodoList.API.TodoListApi.Repository.TodoRepository;
import TodoList.API.TodoListApi.Repository.UserRepository;
import TodoList.API.TodoListApi.Utils.GenericResponse;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public TodoServiceImpl(TodoRepository todoRepository, UserRepository userRepository, ModelMapper ModelMapper) {
        this.todoRepository = todoRepository;
        this.userRepository = userRepository;
        this.modelMapper = ModelMapper;
    }

    @Override
    public GenericResponse createTodo(TodoBean todoBean, String email) {
        try {
            UserEntity user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new RuntimeException("User not found"));
            TodoEntity todoEntity = modelMapper.map(todoBean, TodoEntity.class);
            todoEntity.setUser(user);
            todoRepository.save(todoEntity);

            todoBean.setUserName(user.getName());
            return new GenericResponse(0, "Task created successfully", todoBean);
        } catch (Exception e) {
            return new GenericResponse(2, "Error: " + e.getMessage(), null);
        }
    }

    @Override
    public GenericResponse getMyTodos(String email) {
        try {
            UserEntity user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            List<TodoEntity> entities = todoRepository.findByUserId(user.getId());

            List<TodoBean> list = entities.stream()
                    .map(entity -> {
                        TodoBean bean = modelMapper.map(entity, TodoBean.class);
                        bean.setUserName(user.getName());
                        return bean;
                    })
                    .toList();

            return new GenericResponse(0, "Tasks retrieved successfully", list);

        } catch (Exception e) {
            return new GenericResponse(2, "Error: " + e.getMessage(), null);
        }
    }
    @Override
    public GenericResponse deleteTodo(Long id, String email) {
        try {
            Optional<TodoEntity> todoOpt = todoRepository.findById(id);

            if (todoOpt.isPresent()) {
                TodoEntity todo = todoOpt.get();
                if (todo.getUser().getEmail().equals(email)) {
                    todoRepository.delete(todo);
                    return new GenericResponse(0, "Task deleted successfully", null);
                } else {
                    return new GenericResponse(1, "You don't have permission to delete this task", null);
                }
            }
            return new GenericResponse(1, "Task not found", null);
        } catch (Exception e) {
            return new GenericResponse(2, "Error: " + e.getMessage(), null);
        }
    }

    @Override
    public GenericResponse updateTodo(Long id, TodoBean todoBean, String email) {
        try {
            Optional<TodoEntity> todoOpt = todoRepository.findById(id);

            if (todoOpt.isPresent()) {
                TodoEntity todo = todoOpt.get();
                if (todo.getUser().getEmail().equals(email)) {
                    modelMapper.map(todoBean, todo);
                    todoRepository.save(todo);
                    todoBean.setUserName(todo.getUser().getName());
                    return new GenericResponse(0, "Task updated successfully", todoBean);
                }
                return new GenericResponse(1, "Forbidden: Not your task", null);
            }
            return new GenericResponse(1, "Task not found", null);
        } catch (Exception e) {
            return new GenericResponse(2, "Error: " + e.getMessage(), null);
        }
    }

}