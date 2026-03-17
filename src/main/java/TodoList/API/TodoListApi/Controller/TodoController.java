package TodoList.API.TodoListApi.Controller;

import TodoList.API.TodoListApi.Model.TodoBean;
import TodoList.API.TodoListApi.Service.TodoService;
import TodoList.API.TodoListApi.Utils.GenericResponse;
import jakarta.validation.Valid;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/todos")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("/list")
    public GenericResponse listMyTodos() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return todoService.getMyTodos(email);
    }

    @PostMapping(value = "/create")
    public GenericResponse createTodo(@Valid @RequestBody TodoBean todoBean) {
            String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
            return todoService.createTodo(todoBean, userEmail);
    }

    @DeleteMapping("/delete/{id}")
    public GenericResponse delete(@PathVariable Long id) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return todoService.deleteTodo(id, email);
    }

    @PutMapping("/update/{id}")
    public GenericResponse update(@PathVariable Long id,@Valid  @RequestBody TodoBean todoBean) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return todoService.updateTodo(id, todoBean, email);
    }
}