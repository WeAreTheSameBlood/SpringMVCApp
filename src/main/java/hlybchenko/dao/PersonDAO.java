package hlybchenko.dao;

import hlybchenko.models.Person;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private static int PEOPLE_COUNT;

    private static final String URL = "jdbc:postgresql://localhost:5432/test_db_for_crud";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "postgres";

    private static Connection connection;
    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Person> index(){
        List <Person> people = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM Person";
            ResultSet resultSet = statement.executeQuery(SQL);

            while (resultSet.next()){
                Person person = new Person();
                person.setId(resultSet.getInt("id"));
                person.setName(resultSet.getString("name"));
                person.setAge(resultSet.getInt("age"));
                person.setEmail(resultSet.getString("email"));

                people.add(person);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return people;
    }

    public Person show(int id){
        Person person = null;
        String SQL = "SELECT * FROM Person WHERE id=?";
        try {
            PreparedStatement prSt = connection.prepareStatement(SQL);
            prSt.setInt(1, id);
            ResultSet resultSet = prSt.executeQuery();
            resultSet.next();
            person = new Person();
            person.setId(resultSet.getInt("id"));
            person.setName(resultSet.getString("name"));
            person.setAge(resultSet.getInt("age"));
            person.setEmail(resultSet.getString("email"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return person;
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