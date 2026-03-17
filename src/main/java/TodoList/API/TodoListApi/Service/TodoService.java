package TodoList.API.TodoListApi.Service;

import TodoList.API.TodoListApi.Model.TodoBean;
import TodoList.API.TodoListApi.Utils.GenericResponse;

public interface TodoService {
    GenericResponse createTodo(TodoBean todoBean, String email);
    GenericResponse getMyTodos(String email);
    GenericResponse deleteTodo(Long id, String email);
    GenericResponse updateTodo(Long id, TodoBean todoBean, String email);
}
