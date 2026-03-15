package TodoList.API.TodoListApi.Controller;

import TodoList.API.TodoListApi.Model.LoginBean;
import TodoList.API.TodoListApi.Model.UserBean;
import TodoList.API.TodoListApi.Service.UserService;
import TodoList.API.TodoListApi.Utils.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public GenericResponse register(@RequestBody UserBean user) {
        return userService.registerUser(user);
    }

    @PostMapping("/login")
    public GenericResponse login(@RequestBody LoginBean loginBean){
        return userService.loginUser(loginBean);
    }
}