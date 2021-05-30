package ru.example.springmvc.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.example.springmvc.models.Person;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class MyPersonDAO {
    private static int PERSON_COUNT;
    private final JdbcTemplate template;

    @Autowired
    public MyPersonDAO(JdbcTemplate template) {
        this.template = template;
    }


    public void addNewPerson(Person person) {
        template.update("insert into person values(?,?,?,?,?)",new Object[]{person.getId(),
        person.getName(),
        person.getSurname(),
        person.getAge(),
        person.getEmail()});
    }

    public List<Person> getPeople() {
        return template.query("select * from person",new PersonMapper());
    }

    public Person getPerson(int id) throws SQLException {
        return template.query("select * from person where id=?",new Object[]{id},new PersonMapper()).stream().findAny().orElse(null);
    }

    public void update(Person updatedPerson,int id) throws SQLException {
        template.update("update person set name=?, surname=?, age=?, email=? where id=?",
                new Object[]{updatedPerson.getName(),
                        updatedPerson.getSurname(),
                        updatedPerson.getAge(),
                        updatedPerson.getEmail(),
                        id});
    }

    public void deletePerson(int id) throws SQLException {
        template.update("delete from person where id=?",new Object[]{id});
    }
}
