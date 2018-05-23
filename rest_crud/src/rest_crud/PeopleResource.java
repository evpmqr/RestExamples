package rest_crud;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

/**
 * http://localhost:8080/rest_crud/rest/people
 */
@Path("/people")
public class PeopleResource {

	@Context
	UriInfo uriInfo;
	
	@Context
	Request request;
	
	PersonDao personDao = PersonDao.instance;
	public PeopleResource() {	
	}
	
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<Person> getPeople(){
		return personDao.getPeopleAsList();
	}
	
	@GET
	@Produces(MediaType.TEXT_XML)
	public List<Person> getPeopleAsHTML(){
		return personDao.getPeopleAsList();
	}
	
	/**
	 * http://localhost:8080/rest_crud/rest/people/count
	 * @returns number of people
	 */
	@GET
	@Path("count")
	@Produces(MediaType.TEXT_PLAIN)
	public String getCount() {
		return String.valueOf(personDao.getPersonCount());
	}
	
	/**
	 * 
	 * @param id
	 * @param name
	 * @param age
	 * @param servletResponse
	 * @throws IOException
	 * 
	 * Handles POST requests and creates a new person and add it to our DAO
	 */
	@POST
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void createPerson(@FormParam("id") String id,
			@FormParam("name") String name,
			@FormParam("age") int age,
			@Context HttpServletResponse servletResponse) throws IOException {
		System.out.println("Create person in people resource");
		Person person = new Person(name, age, id);
		personDao.addPerson(person);
		servletResponse.sendRedirect("./people/");
	}
	
	/**
	 * @param id
	 * @return PersonResource
	 * 
	 * Sends any requests for a single person to the PersonResource class.
	 * Takes an id and returns a new PersonResource based on the id.
	 * 
	 * ex: http://localhost:8080/rest_crud/rest/people/1
	 */
	@Path("{personid}")
	public PersonResource getPerson(@PathParam("personid") String id) {
		return new PersonResource(uriInfo, request, id);
	}
}
