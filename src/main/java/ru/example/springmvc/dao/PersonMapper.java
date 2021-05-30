package ru.example.springmvc.dao;

import org.springframework.jdbc.core.RowMapper;
import ru.example.springmvc.models.Person;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonMapper implements RowMapper<Person> {
    @Override
    public Person mapRow(ResultSet set, int i) throws SQLException {
        Person person = new Person();
        person.setId(set.getInt("id"));
        person.setName(set.getString("name"));
        person.setSurname(set.getString("surname"));
        person.setAge(set.getInt("age"));
        person.setEmail(set.getString("email"));
        return person;
    }
}
