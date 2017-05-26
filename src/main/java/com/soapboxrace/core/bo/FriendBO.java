package com.soapboxrace.core.bo;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.soapboxrace.core.dao.PersonaDAO;
import com.soapboxrace.core.jpa.PersonaEntity;
import com.soapboxrace.jaxb.http.FriendPersona;
import com.soapboxrace.jaxb.xmpp.XMPP_FriendPersonaType;
import com.soapboxrace.jaxb.xmpp.XMPP_FriendResultType;
import com.soapboxrace.jaxb.xmpp.XMPP_ResponseTypeFriendResult;
import com.soapboxrace.xmpp.openfire.XmppFriend;

@Stateless
public class FriendBO {

	@EJB
	private PersonaDAO personaDao;
	
	@EJB
	private DriverPersonaBO personaBO;

	public void sendFriendRequest(Long personaId, String displayName, String reqMessage) {
		PersonaEntity personaEntity = personaDao.findById(personaId);
		PersonaEntity personaInvite = personaDao.findByName(displayName);
		
		if(personaEntity != null && personaInvite != null) {
			XMPP_FriendPersonaType friendPersonaType = new XMPP_FriendPersonaType();
			friendPersonaType.setIconIndex(personaEntity.getIconIndex());
			friendPersonaType.setLevel(personaEntity.getLevel());
			friendPersonaType.setName(personaEntity.getName());
			friendPersonaType.setOriginalName(personaEntity.getName());
			friendPersonaType.setPersonaId(personaEntity.getPersonaId());
			friendPersonaType.setPresence(3);
			friendPersonaType.setUserId(personaEntity.getUser().getId());
			
			XmppFriend xmppFriend = new XmppFriend(personaInvite.getPersonaId());
			xmppFriend.sendFriendRequest(friendPersonaType);
		}
	}
	
	public void sendResponseFriendRequest(Long personaId, Long friendPersonaId, int resolution) {
		// Send all info to friendPersonaId
		FriendPersona friendPersona = new FriendPersona();
		friendPersona.setPersonaId(personaId);
		
		XMPP_FriendResultType friendResultType = new XMPP_FriendResultType();
		friendResultType.setFriendPersona(friendPersona);
		friendResultType.setResult(resolution);
		
		XMPP_ResponseTypeFriendResult responseTypeFriendResult = new XMPP_ResponseTypeFriendResult();
		responseTypeFriendResult.setFriendResult(friendResultType);
		
		XmppFriend xmppFriend = new XmppFriend(friendPersonaId);
		xmppFriend.sendResponseFriendRequest(responseTypeFriendResult);
		
		// Send all info to personaId
		XMPP_FriendPersonaType friendPersonaType = new XMPP_FriendPersonaType();
		friendPersonaType.setPersonaId(friendPersonaId);
		friendPersonaType.setPresence(1);
		
		XmppFriend xmppFriend2 = new XmppFriend(personaId);
		xmppFriend2.sendFriendRequest(friendPersonaType);
	}

}
