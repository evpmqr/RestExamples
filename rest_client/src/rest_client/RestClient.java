package rest_client;

import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;

public class RestClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ClientConfig config = new ClientConfig();
		
		Client client = ClientBuilder.newClient(config);
		
		WebTarget target = client.target(getBaseURI());
		
		//Prints out the request of /rest/hello to the server
		System.out.println(target.path("rest").path("hello").
				request().accept(MediaType.TEXT_XML).get(String.class)); 
	
	}

	private static URI getBaseURI() {
		return UriBuilder.fromUri("http://localhost:8080/restproject/").build();
	}
}
