package TodoList.API.TodoListApi.Service;

import TodoList.API.TodoListApi.Model.LoginBean;
import TodoList.API.TodoListApi.Model.UserBean;
import TodoList.API.TodoListApi.Utils.GenericResponse;

public interface UserService {
    GenericResponse registerUser(UserBean userBean);
    GenericResponse loginUser(LoginBean loginBean);
}
