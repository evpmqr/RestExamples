package people_client;

import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;

public class PeopleClient {

	private static URI getBaseURI() {
		return UriBuilder.fromUri("http://localhost:8080/rest_crud").build();
	}
	
	public static void main(String[] args) {
		ClientConfig config = new ClientConfig();
		
		Client client = ClientBuilder.newClient(config);
		
		WebTarget target = client.target(getBaseURI());
		
		//Access the people resource and et the person wih id 1
		System.out.println(target.path("rest").path("people").path("1")
				.request().accept(MediaType.TEXT_XML).get(String.class));
		
		//Deletes person with id of 1
		target.path("rest").path("people").path("1")
		.request().delete(String.class);
		
		//Tries to get the person we just deleted. Should just be a blank line
		System.out.println(target.path("rest").path("people").path("1")
				.request().accept(MediaType.TEXT_XML).get(String.class));

		//This will allow us to make POST requests
		MultivaluedMap<String, String> form = new MultivaluedHashMap<>();
		form.add("name", "Michael");
		form.add("age", "21");
		form.add("id", "3");
		
		//sends a post request to the webservice
		target.path("rest").path("people").request().post(Entity.form(form));
		System.out.println("Post request sent");
		
		//Get the person we just created
		System.out.println(target.path("rest").path("people").path("3")
				.request().accept(MediaType.TEXT_XML).get(String.class));
	}

}
