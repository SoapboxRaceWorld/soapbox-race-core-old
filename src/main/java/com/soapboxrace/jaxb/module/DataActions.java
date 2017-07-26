package com.soapboxrace.jaxb.module;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DataActions implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 19394940126734799L;

	public DataActions() {
		terminateOn.add(new TerminateOn("ExecutableHash"));
		terminateOn.add(new TerminateOn("ExecutableVersion"));
		activateModules.add(new ActivateModules("UDCRCBypass"));
		activateModules.add(new ActivateModules("UDPCryptFunctionBypass_OnRead"));
		activateModules.add(new ActivateModules("UDPCryptFunctionBypass_OnSend"));
		activateModules.add(new ActivateModules("XMPPCRCBypass"));
		activateModules.add(new ActivateModules("Anticheat"));
		activateModules.add(new ActivateModules("D3D9Menu"));
		activateModules.add(new ActivateModules("NewSafehouses"));
	}

	private List<TerminateOn> terminateOn = new ArrayList<>();

	private List<ActivateModules> activateModules = new ArrayList<>();

	public List<TerminateOn> getTerminateOn() {
		return terminateOn;
	}

	public void setTerminateOn(List<TerminateOn> terminateOn) {
		this.terminateOn = terminateOn;
	}

	public List<ActivateModules> getActivateModules() {
		return activateModules;
	}

	public void setActivateModules(List<ActivateModules> activateModules) {
		this.activateModules = activateModules;
	}

}
