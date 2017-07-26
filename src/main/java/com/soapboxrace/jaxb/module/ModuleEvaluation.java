package com.soapboxrace.jaxb.module;

import java.io.Serializable;

public class ModuleEvaluation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9142974048308581921L;
	private ModuleEvaluationResult moduleEvaluationResult = new ModuleEvaluationResult();

	public ModuleEvaluationResult getModuleEvaluationResult() {
		return moduleEvaluationResult;
	}

	public void setModuleEvaluationResult(ModuleEvaluationResult moduleEvaluationResult) {
		this.moduleEvaluationResult = moduleEvaluationResult;
	}

}
