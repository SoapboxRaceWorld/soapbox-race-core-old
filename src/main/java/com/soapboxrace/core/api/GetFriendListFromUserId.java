package com.soapboxrace.core.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.soapboxrace.core.api.util.Secured;
import com.soapboxrace.jaxb.http.ArrayOfFriendPersona;
import com.soapboxrace.jaxb.http.FriendPersona;
import com.soapboxrace.jaxb.http.PersonaFriendsList;

@Path("/getfriendlistfromuserid")
public class GetFriendListFromUserId {

	@GET
	@Secured
	@Produces(MediaType.APPLICATION_XML)
	public PersonaFriendsList getFriendListFromUserId(@QueryParam("userId") Long userId) {
		FriendPersona friendPersona = new FriendPersona();
		friendPersona.setIconIndex(5);
		friendPersona.setLevel(60);
		friendPersona.setName("TEST");
		friendPersona.setOriginalName("TEST2");
		friendPersona.setPersonaId(101);
		friendPersona.setPresence(1);
		friendPersona.setSocialNetwork(1);
		friendPersona.setUserId(65);
		
		ArrayOfFriendPersona arrayOfFriendPersona = new ArrayOfFriendPersona();
		arrayOfFriendPersona.getFriendPersona().add(friendPersona);
		
		PersonaFriendsList personaFriendsList = new PersonaFriendsList();
		personaFriendsList.setFriendPersona(arrayOfFriendPersona);
		return personaFriendsList;
	}
}
