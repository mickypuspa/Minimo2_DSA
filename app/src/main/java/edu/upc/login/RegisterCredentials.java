package edu.upc.login;

public class RegisterCredentials {

    public String nombre;
    public String mail;
    public String password;
    public String confirm;

    public String getNombre() {
        return nombre;
    }

    public String getMail() {
        return mail;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirm() {
        return confirm;
    }

    public RegisterCredentials(){}

    public RegisterCredentials(String nombre, String mail, String password,String confirm) {
        this();
        this.nombre = nombre;
        this.mail = mail;
        this.password = password;
        this.confirm = confirm;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setMail(String mail) { this.mail = mail; }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setConfirm(String confirm) {this.confirm = confirm; }
}
