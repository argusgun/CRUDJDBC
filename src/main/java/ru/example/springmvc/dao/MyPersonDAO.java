package ru.example.springmvc.dao;

import ru.example.springmvc.models.Person;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class MyPersonDAO {
    private static int PERSON_COUNT;
    private static final String URL="jdbc:postgresql://localhost:5432/db_name";
    private static final String USERNAME="postgres";
    private static final String PASSWORD="post123";
    private static Connection connection;
    {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection= DriverManager.getConnection(URL,USERNAME,PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public void addNewPerson(Person person) throws SQLException {
        PreparedStatement st = connection.prepareStatement("insert into person values(?,?,?,?,?)");
        st.setInt(1,++PERSON_COUNT);
        st.setString(2,person.getName());
        st.setString(3,person.getSurname());
        st.setInt(4,person.getAge());
        st.setString(5,person.getEmail());
        st.executeUpdate();
    }

    public List<Person> getPeople() throws SQLException {

        List<Person> people =new ArrayList<>();
        Statement statement=connection.createStatement();
        String SQL="select * from person";
        ResultSet set = statement.executeQuery(SQL);
        while (set.next()){
            Person person = new Person();
            person.setId(set.getInt("id"));
            person.setName(set.getString("name"));
            person.setSurname(set.getString("surname"));
            person.setAge(set.getInt("age"));
            person.setEmail(set.getString("email"));
            people.add(person);
        }
        PERSON_COUNT=people.size();
        return people;
    }

    public Person getPerson(int id) throws SQLException {
        Person person = new Person();
        PreparedStatement st = connection.prepareStatement("select * from person where id=?");
        st.setInt(1,id);
        ResultSet set = st.executeQuery();
        set.next();
        System.out.println("id="+id+"\n"+"name="+set.getString("name"));
        person.setId(id);
        person.setName(set.getString("name"));
        person.setSurname(set.getString("surname"));
        person.setAge(set.getInt("age"));
        person.setEmail(set.getString("email"));
        return person;
    }

    public void update(Person updatedPerson,int id) throws SQLException {
        PreparedStatement st = connection.prepareStatement("update person set name=?, surname=?, age=?, email=? where id=?");
        st.setString(1,updatedPerson.getName());
        st.setString(2,updatedPerson.getSurname());
        st.setInt(3,updatedPerson.getAge());
        st.setString(4,updatedPerson.getEmail());
        st.setInt(5,id);
        st.executeUpdate();
    }

    public void deletePerson(int id) throws SQLException {
        PreparedStatement st = connection.prepareStatement("delete from person where id=?");
        st.setInt(1,id);
        st.executeUpdate();
    }
}
