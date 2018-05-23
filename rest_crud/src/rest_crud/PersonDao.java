package rest_crud;

import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
public enum PersonDao {
	instance;
	
	private Map<String, Person> people = new HashMap<String, Person>();
	
	private PersonDao() {
		Person person = new Person("James", 26, "1");
		people.put(person.getID(), person);
		
		person = new Person("Mary", 32, "2");
		people.put(person.getID(), person);
				
	}
	
	public void addPerson(Person person) {
		people.put(person.getID(), person);
	}
	
	public Person getPerson(String id) {
		return people.get(id);
	}
	
	public int getPersonCount() {
		return people.size();
	}
	
	public List<Person> getPeopleAsList(){
		List<Person> personList = new ArrayList<>();
		personList.addAll(people.values());
		return personList;
	}
	
	public Map<String, Person> getPeople(){
		return people;
	}
	
	public void deletePerson(String id) {
		people.remove(id);
	}
	
	
}
