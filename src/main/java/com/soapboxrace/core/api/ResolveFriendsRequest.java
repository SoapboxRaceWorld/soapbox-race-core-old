package com.soapboxrace.core.api;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.soapboxrace.core.api.util.Secured;
import com.soapboxrace.core.bo.FriendBO;

@Path("/resolvefriendsrequest")
public class ResolveFriendsRequest {
	
	@EJB
	private FriendBO bo;

	@GET
	@Secured
	@Produces(MediaType.APPLICATION_XML)
	public String resolvefriendsrequest(@QueryParam("personaId") Long personaId, @QueryParam("friendPersonaId") Long friendPersonaId, @QueryParam("resolution") int resolution) {
		bo.sendResponseFriendRequest(personaId, friendPersonaId, resolution);
		return "";
	}

}
