package com.soapboxrace.xmpp.openfire;

import com.soapboxrace.jaxb.xmpp.XMPP_FriendPersonaType;

public class XmppFriend {

	private long personaId;
	private static OpenFireSoapBoxCli openFireSoapBoxCli = OpenFireSoapBoxCli.getInstance();

	public XmppFriend(long personaId) {
		this.personaId = personaId;
	}

	public void sendFriendRequest(XMPP_FriendPersonaType friendPersonaType) {
		openFireSoapBoxCli.send(friendPersonaType, personaId);
	}

}
