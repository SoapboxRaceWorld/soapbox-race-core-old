package com.soapboxrace.core.api.util;

import com.soapboxrace.core.bo.AuthenticationBO;
import com.soapboxrace.core.jpa.BanEntity;
import com.soapboxrace.jaxb.login.LoginStatusVO;

import javax.annotation.Priority;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@LauncherChecks
@Provider
@Priority(Priorities.AUTHORIZATION)
public class LaunchFilter implements ContainerRequestFilter {
	@EJB
	private AuthenticationBO authenticationBO;
	
	@Context
	private HttpServletRequest sr;

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		BanEntity banEntity = authenticationBO.checkNonUserBan(RequestUtil.getIp(sr), sr.getParameter("email"));

		if (banEntity != null)
		{
			LoginStatusVO loginStatusVO = BanUtil.getLoginStatus(banEntity);

			requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity(loginStatusVO).build());
		}
	}
}
