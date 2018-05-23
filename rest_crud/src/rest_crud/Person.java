package rest_crud;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Person {

	
	private String name;
	private int age;
	private String id;
	
	public Person() {}
	
	public Person(String name, int age, String id) {
		this.name = name;
		this.age = age;
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public int getAge() {
		return age;
	}
	
	public String getID() {
		return id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public void setID(String id) {
		this.id = id;
	}
}
