package TodoList.API.TodoListApi.Model;

import lombok.Data;
import org.springframework.security.core.userdetails.User;

@Data
public class TodoBean {
    private String title;
    private String description;
    private User user;
}
