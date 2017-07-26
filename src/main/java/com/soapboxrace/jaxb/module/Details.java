package com.soapboxrace.jaxb.module;

import java.io.Serializable;

public class Details implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6105231945067179451L;

	private DataMatch dataMatch = new DataMatch();

	private DataActions dataActions = new DataActions();

	public DataMatch getDataMatch() {
		return dataMatch;
	}

	public void setDataMatch(DataMatch dataMatch) {
		this.dataMatch = dataMatch;
	}

	public DataActions getDataActions() {
		return dataActions;
	}

	public void setDataActions(DataActions dataActions) {
		this.dataActions = dataActions;
	}

}
