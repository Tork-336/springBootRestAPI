package com.applicationAPI.controller.Form;

import javax.validation.constraints.NotNull;

public class LoginForm {

    @NotNull(message = "El campo es requerido.")
    private String username;
    @NotNull(message = "El campo es requerido.")
    private String password;

    public LoginForm() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
