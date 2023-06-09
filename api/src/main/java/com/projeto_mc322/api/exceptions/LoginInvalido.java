package com.projeto_mc322.api.exceptions;

public class LoginInvalido extends Exception{
    public LoginInvalido() {
    }

    public LoginInvalido(String message) {
        super(message);
    }
}
