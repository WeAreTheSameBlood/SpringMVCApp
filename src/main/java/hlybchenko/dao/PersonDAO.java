package hlybchenko.dao;

import hlybchenko.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index(){
        return jdbcTemplate.query("SELECT * FROM Person", new PersonMapper());
    }

    public Person show(int id){
        return jdbcTemplate.query("SELECT * FROM Person WHERE id=?", new Object[] {id}, new PersonMapper())
                .stream().findAny().orElse(null);
    }

    public void save(Person person) {
        String SQL = "INSERT INTO Person VALUES (1, ?, ?, ?)";
        try {
            PreparedStatement prSt = connection.prepareStatement(SQL);
            prSt.setString(1, person.getName());
            prSt.setInt(2, person.getAge());
            prSt.setString(3, person.getEmail());
            prSt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(int id, Person updatedPerson) {
        try {
            PreparedStatement prSt =
                    connection.prepareStatement("UPDATE Person SET name =?, age=?, email=? WHERE  id=?");
            prSt.setString(1, updatedPerson.getName());
            prSt.setInt(2, updatedPerson.getAge());
            prSt.setString(3, updatedPerson.getEmail());
            prSt.setInt(4, id);
            prSt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(int id) {
        try {
            PreparedStatement prSt =
                    connection.prepareStatement("DELETE FROM Person WHERE id=?");
            prSt.setInt(1, id);
            prSt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}