package hlybchenko.dao;

import hlybchenko.models.Person;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private static int PEOPLE_COUNT;
    private List<Person> people;

    {
        people = new ArrayList<>();
        people.add(new Person(++PEOPLE_COUNT, "Andrii", 23, "hlybch@gmail.com"));
        people.add(new Person(++PEOPLE_COUNT, "Serhii", 24, "REDCher@gmail.com"));
        people.add(new Person(++PEOPLE_COUNT, "Liza", 23, "leglize@gmail.com"));
        people.add(new Person(++PEOPLE_COUNT, "Sasha", 22, "sascha@gmail.com"));
        people.add(new Person(++PEOPLE_COUNT, "Dima", 21, "vergus@gmail.com"));
    }

    public List<Person> index(){
        return people;
    }

    public Person show(int id){
        return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
    }

    public void save(Person person) {
        person.setId(++PEOPLE_COUNT);
        people.add(person);
    }

    public void update(int id, Person UpdatedPerson) {
        Person personToBeUpdated = show(id);
        personToBeUpdated.setName(UpdatedPerson.getName());
        personToBeUpdated.setAge(UpdatedPerson.getAge());
        personToBeUpdated.setEmail(UpdatedPerson.getEmail());
    }

    public void delete(int id) {
        people.removeIf(p -> p.getId() == id);
    }
}