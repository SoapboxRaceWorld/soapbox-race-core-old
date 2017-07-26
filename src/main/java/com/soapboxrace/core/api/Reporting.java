package com.soapboxrace.core.api;

import java.io.InputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.soapboxrace.core.api.util.Secured;
import com.soapboxrace.jaxb.http.HardwareInfo;
import com.soapboxrace.jaxb.module.ModuleEvaluation;
import com.soapboxrace.jaxb.module.ModuleInformation;
import com.soapboxrace.jaxb.util.UnmarshalXML;

@Path("/Reporting")
public class Reporting {

	@POST
	@Secured
	@Path("/SendHardwareInfo")
	@Produces(MediaType.APPLICATION_XML)
	public String sendHardwareInfo(InputStream is) {
		HardwareInfo unMarshal = (HardwareInfo) UnmarshalXML.unMarshal(is, HardwareInfo.class);
		System.out.println(unMarshal);
		return "";
	}

	@POST
	@Secured
	@Path("/SendUserSettings")
	@Produces(MediaType.APPLICATION_XML)
	public String sendUserSettings() {
		return "";
	}

	@GET
	@Secured
	@Path("/SendMultiplayerConnect")
	@Produces(MediaType.APPLICATION_XML)
	public String sendMultiplayerConnect() {
		return "";
	}

	@GET
	@Secured
	@Path("/SendClientPingTime")
	@Produces(MediaType.APPLICATION_XML)
	public String sendClientPingTime() {
		return "";
	}

	@GET
	@Path("{path:.*}")
	@Produces(MediaType.APPLICATION_XML)
	public String genericEmptyGet(@PathParam("path") String path) {
		System.out.println("empty GET!!!");
		return "";
	}

	@POST
	@Path("{path:.*}")
	@Produces(MediaType.APPLICATION_XML)
	public String genericEmptyPost(@PathParam("path") String path) {
		System.out.println("empty POST!!!");
		return "";
	}

	@PUT
	@Path("{path:.*}")
	@Produces(MediaType.APPLICATION_XML)
	public String genericEmptyPut(@PathParam("path") String path) {
		System.out.println("empty PUT!!!");
		return "";
	}

	@POST
	@Path("/ModuleInformation")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ModuleEvaluation moduleInformation(ModuleInformation moduleInfo) {
		System.out.println(moduleInfo.getModuleInfo().getName());
		ModuleEvaluation moduleEvaluation = new ModuleEvaluation();
		return moduleEvaluation;
	}

	@GET
	@Path("/ModuleInformationTest")
	@Produces(MediaType.APPLICATION_JSON)
	public ModuleInformation moduleInformationTest() {
		ModuleInformation moduleInformation = new ModuleInformation();
		return moduleInformation;
	}

}
