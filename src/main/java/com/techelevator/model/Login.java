package com.techelevator.model;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;

public class Login {

    @NotBlank(message = "Please enter a username")
    private String userName;

    @NotBlank(message = "Please enter a password")
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
