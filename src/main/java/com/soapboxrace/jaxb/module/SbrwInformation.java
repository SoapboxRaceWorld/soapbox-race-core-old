package com.soapboxrace.jaxb.module;

import java.io.Serializable;

public class SbrwInformation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2869772671478278435L;
	private String executablePath = "D:\\_corsair\\NFSW\\Data\\nfsw.exe";
	private String executablePathCommandLine = "D:\\_corsair\\NFSW\\Data\\nfsw.exe";
	private String executableVersion = "1.0.0.1613";
	private String commandLine = "D:\\_corsair\\NFSW\\Data\\nfsw.exe\" EU http://127.0.0.1:64145/ a 1";
	private boolean internalCheckResult = true;

	public String getExecutablePath() {
		return executablePath;
	}

	public void setExecutablePath(String executablePath) {
		this.executablePath = executablePath;
	}

	public String getExecutablePathCommandLine() {
		return executablePathCommandLine;
	}

	public void setExecutablePathCommandLine(String executablePathCommandLine) {
		this.executablePathCommandLine = executablePathCommandLine;
	}

	public String getExecutableVersion() {
		return executableVersion;
	}

	public void setExecutableVersion(String executableVersion) {
		this.executableVersion = executableVersion;
	}

	public String getCommandLine() {
		return commandLine;
	}

	public void setCommandLine(String commandLine) {
		this.commandLine = commandLine;
	}

	public boolean isInternalCheckResult() {
		return internalCheckResult;
	}

	public void setInternalCheckResult(boolean internalCheckResult) {
		this.internalCheckResult = internalCheckResult;
	}

}
