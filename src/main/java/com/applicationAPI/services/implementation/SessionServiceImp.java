package com.applicationAPI.services.implementation;

import com.applicationAPI.controller.Form.LoginForm;
import com.applicationAPI.execption.ModelNotFoundExecption;
import com.applicationAPI.repository.UserRepository;
import com.applicationAPI.repository.entities.User;
import com.applicationAPI.services.interfaces.SessionService;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class SessionServiceImp implements SessionService {

    private final UserRepository repository;

    public SessionServiceImp(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public LoginForm login(LoginForm user) {
        LoginForm userSession = new LoginForm();
        User userValidate = repository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
        if (Objects.nonNull(userValidate)) {
            System.out.println(" User validate: " + userValidate);
            userSession.setUsername(userValidate.getUsername());
            userSession.setPassword(userValidate.getPassword());
        } else {
            throw new ModelNotFoundExecption(" Usuario no encontrado.");
        }
        return userSession;
    }
}
