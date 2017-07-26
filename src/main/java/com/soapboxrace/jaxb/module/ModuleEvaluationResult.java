package com.soapboxrace.jaxb.module;

import java.io.Serializable;

public class ModuleEvaluationResult implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4896551086074817362L;
	private String moduleSpecifier = "ANewWorld";
	private Details details = new Details();

	public String getModuleSpecifier() {
		return moduleSpecifier;
	}

	public void setModuleSpecifier(String moduleSpecifier) {
		this.moduleSpecifier = moduleSpecifier;
	}

	public Details getDetails() {
		return details;
	}

	public void setDetails(Details details) {
		this.details = details;
	}

}
