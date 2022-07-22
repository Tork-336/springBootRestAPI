package com.applicationAPI.services.interfaces;

import com.applicationAPI.controller.Form.LoginForm;

public interface SessionService {

    LoginForm login(LoginForm user);
}
