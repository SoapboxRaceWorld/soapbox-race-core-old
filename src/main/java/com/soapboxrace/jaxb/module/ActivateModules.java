package com.soapboxrace.jaxb.module;

import java.io.Serializable;

public class ActivateModules implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1947109138551421671L;
	private String moduleSpecifier;

	public ActivateModules(String moduleSpecifier) {
		this.moduleSpecifier = moduleSpecifier;
	}

	public String getModuleSpecifier() {
		return moduleSpecifier;
	}

	public void setModuleSpecifier(String moduleSpecifier) {
		this.moduleSpecifier = moduleSpecifier;
	}

}
