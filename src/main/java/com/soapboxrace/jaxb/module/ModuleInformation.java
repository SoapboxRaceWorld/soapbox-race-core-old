package com.soapboxrace.jaxb.module;

import java.io.Serializable;

public class ModuleInformation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -196791136942879626L;

	private ModuleInfo moduleInfo = new ModuleInfo();

	public ModuleInfo getModuleInfo() {
		return moduleInfo;
	}

	public void setModuleInfo(ModuleInfo moduleInfo) {
		this.moduleInfo = moduleInfo;
	}

}
