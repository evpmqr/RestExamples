package rest_person;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/person")
public class PersonResource {

	@GET
	@Produces(MediaType.APPLICATION_XML)
	public Person getXML() {
		Person person = new Person();
		person.setName("James");
		person.setAge(25);
		return person;
	}
}
