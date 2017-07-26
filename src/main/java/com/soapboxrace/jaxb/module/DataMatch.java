package com.soapboxrace.jaxb.module;

import java.io.Serializable;

public class DataMatch implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3968219056301237098L;
	private boolean executableHash = false;
	private boolean executableVersion = false;
	private boolean gameplayNativeHash = false;
	private boolean aNewWorldHash = false;
	private boolean scriptHookWorldHash = false;

	public boolean isExecutableHash() {
		return executableHash;
	}

	public void setExecutableHash(boolean executableHash) {
		this.executableHash = executableHash;
	}

	public boolean isExecutableVersion() {
		return executableVersion;
	}

	public void setExecutableVersion(boolean executableVersion) {
		this.executableVersion = executableVersion;
	}

	public boolean isGameplayNativeHash() {
		return gameplayNativeHash;
	}

	public void setGameplayNativeHash(boolean gameplayNativeHash) {
		this.gameplayNativeHash = gameplayNativeHash;
	}

	public boolean isaNewWorldHash() {
		return aNewWorldHash;
	}

	public void setaNewWorldHash(boolean aNewWorldHash) {
		this.aNewWorldHash = aNewWorldHash;
	}

	public boolean isScriptHookWorldHash() {
		return scriptHookWorldHash;
	}

	public void setScriptHookWorldHash(boolean scriptHookWorldHash) {
		this.scriptHookWorldHash = scriptHookWorldHash;
	}

}
