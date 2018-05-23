package rest_crud;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBElement;
/**
 * Responsible for handling requests for a Person resource. Gets person DAO instead of 
 * creating a new instance. No path because requests will not go directly to this resource, 
 * instead they will be accessing the PeopleResource class.
 */
public class PersonResource {
	
	@Context
	UriInfo uriInfo;
	
	@Context
	Request request;
	String id;
	
	PersonDao personDao = PersonDao.instance;
	
	public PersonResource(UriInfo uriInfo, Request request, String id) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.id = id;
	}
	
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Person getPerson() {
		Person person = personDao.getPerson(id);
		return person;
	}
	
	@GET
	@Produces({MediaType.TEXT_XML})
	public Person getPersonAsHTML() {
		Person person = personDao.getPerson(id);
		return person;
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	public Response putPerson(JAXBElement<Person> personElement) {
		Person person = personElement.getValue();
		Response response;
		
		if(personDao.getPeople().containsKey(person.getID())) {
			response = Response.noContent().build();
		} else {
			response = Response.created(uriInfo.getAbsolutePath()).build();
		}
		
		personDao.addPerson(person);
		return response;
	}
	
	@DELETE
	public void deletePerso() {
		personDao.deletePerson(id);
	}
}
