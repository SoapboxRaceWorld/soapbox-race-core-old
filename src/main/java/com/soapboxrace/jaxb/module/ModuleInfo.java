package com.soapboxrace.jaxb.module;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ModuleInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4923955235014912100L;
	private String name = "ANewWorld";
	private String version = "1.0.0.0";
	private SbrwInformation sbrwInformation = new SbrwInformation();
	private Map<String, String> hashes = new HashMap<>();

	public ModuleInfo() {
		hashes.put("Executable", "028d6d132c20b69f85119e31f2964f619fd95549bcb1abf901228a9c899ac932e1dbedd080aa0fa32d685412e04acd5d9176025b29dd3d7cb52bf9f890c6b35c");
		hashes.put("GameplayNative", "43fc383a54e39a7f65a5bc7c867d2dbdab76807c5ab16f20ee82a218db4b33646096b95b5607f323e8e833343c1e1a66f38ad45fd7561f28e9677246012d3cf2");
		hashes.put("LightFX", "b1358c634d327a373c3d4e4d9489df2d6422046c85e0341b393ffd0dce528c1184e225866d49c9d472b4a0130988e30e99d7ad8555a005837e70c18685e78842");
		hashes.put("ScriptHookWorld", "64d202b2263757b25d2ef831970ff866d3545dcf7b563fbd0b938931c276e102b7f28e07048b4fada8896877b20cce9f1742cacfe6933be3aeee92cdea47e6ae");
		hashes.put("ANewWorld", "0baa296d040801dfc434ce08a185ec165c9a97d1c6e711feae34d8face9019eb50b3e4a4e4fed1d74991d0520764c2cb1721ba4ae58ad6e48495d1427859dfa4");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public SbrwInformation getSbrwInformation() {
		return sbrwInformation;
	}

	public void setSbrwInformation(SbrwInformation sbrwInformation) {
		this.sbrwInformation = sbrwInformation;
	}

	public Map<String, String> getHashes() {
		return hashes;
	}

	public void setHashes(Map<String, String> hashes) {
		this.hashes = hashes;
	}

}
