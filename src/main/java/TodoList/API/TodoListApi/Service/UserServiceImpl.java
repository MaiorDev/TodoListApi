package TodoList.API.TodoListApi.Service;


import TodoList.API.TodoListApi.Entity.UserEntity;
import TodoList.API.TodoListApi.Model.LoginBean;
import TodoList.API.TodoListApi.Model.UserBean;
import TodoList.API.TodoListApi.Repository.UserRepository;
import TodoList.API.TodoListApi.Security.util.JwtUtils;
import TodoList.API.TodoListApi.Utils.GenericResponse;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtils jwtUtils) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public GenericResponse registerUser(UserBean userBean) {
        if (userRepository.findByEmail(userBean.getEmail()).isPresent()) {
            return new GenericResponse(1, "El correo electrónico ya está en uso.", null);
        }

        try {
            UserEntity userEntity = new UserEntity();
            userEntity.setName(userBean.getName());
            userEntity.setEmail(userBean.getEmail());

            String passwordHasheada = passwordEncoder.encode(userBean.getPassword());
            userEntity.setPassword(passwordHasheada);

            userRepository.save(userEntity);
            return new GenericResponse(0, "Usuario creado correctamente", userBean);

        } catch (Exception e) {
            return new GenericResponse(2, "Error interno al registrar: " + e.getMessage(), null);
        }
    }


    @Override
    public GenericResponse loginUser(LoginBean loginBean) {
        Optional<UserEntity> userOpt = userRepository.findByEmail(loginBean.getEmail());
        if (userOpt.isPresent() && passwordEncoder.matches(loginBean.getPassword(), userOpt.get().getPassword())) {
            String token = jwtUtils.generateToken(loginBean.getEmail());
            return new GenericResponse(0, "Login successful", token);
        }
        return new GenericResponse(1, "Invalid credentials", null);
    }


}