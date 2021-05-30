package ru.example.springmvc.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Person {
    private int id;
    @NotEmpty(message = "Не заполнено поле Имя")
    @Size(min = 3,max= 15,message = "Поле Имя должно содержать от 3 до 15 символов")
    private String name;
    @NotEmpty(message = "Не заполнено поле Фамилия")
    @Size(min = 3,max= 15,message = "Поле Фамилия должно содержать от 3 до 15 символов")
    private String surname;
    @Min(value = 0,message = "Поле Возраст должно быть больше 0")
    private int age;
    @Email
    private String email;

    public Person() {
    }

    public Person(int id, String name, String surname,int age, String email) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.surname = surname;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
