package com.soapboxrace.jaxb.module;

import java.io.Serializable;

public class TerminateOn implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3593114582482504322L;
	private String mismatch;

	public TerminateOn(String mismatch) {
		this.mismatch = mismatch;
	}

	public String getMismatch() {
		return mismatch;
	}

	public void setMismatch(String mismatch) {
		this.mismatch = mismatch;
	}

}
