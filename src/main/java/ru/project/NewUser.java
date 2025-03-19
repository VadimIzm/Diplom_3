package ru.project;

public class NewUser extends Endpoints{
    public String email;
    public String password;
    public String name;

    public NewUser(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }
}