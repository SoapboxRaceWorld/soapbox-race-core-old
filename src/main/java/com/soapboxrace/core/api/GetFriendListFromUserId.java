package com.soapboxrace.core.api;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.soapboxrace.core.api.util.Secured;
import com.soapboxrace.core.bo.FriendBO;
import com.soapboxrace.jaxb.http.PersonaFriendsList;

@Path("/getfriendlistfromuserid")
public class GetFriendListFromUserId {
	
	@EJB
	private FriendBO bo;

	@GET
	@Secured
	@Produces(MediaType.APPLICATION_XML)
	public PersonaFriendsList getFriendListFromUserId(@QueryParam("userId") Long userId) {
		return bo.getFriendListFromUserId(userId);
	}
}
