package TodoList.API.TodoListApi.Security.exception;

import TodoList.API.TodoListApi.Utils.GenericResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public GenericResponse handleValidationExceptions(MethodArgumentNotValidException ex) {
        String errorMsg = ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
        return new GenericResponse(1, "Error de validación: " + errorMsg, null);
    }
}