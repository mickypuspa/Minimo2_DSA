package edu.upc.login.Entidades;

import androidx.annotation.NonNull;

public class Token {

    public String token;

    public Token(){}

    public Token(String token) {
        this();
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "Token{" +
                "token='" + token + '\'' +
                '}';
    }
}
