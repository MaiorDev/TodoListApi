package TodoList.API.TodoListApi.Controller;

import TodoList.API.TodoListApi.Model.TodoBean;
import TodoList.API.TodoListApi.Service.TodoService;
import TodoList.API.TodoListApi.Utils.GenericResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/todos")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping(value = "/create")
    public GenericResponse createTodo(@RequestBody TodoBean todoBean) {
        try {
            String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();

            return todoService.createTodo(todoBean, userEmail);
        }catch (Exception e){
            return new GenericResponse(2, "Error interno al crear la tarea: " + e.getMessage(), null);
        }
    }
}