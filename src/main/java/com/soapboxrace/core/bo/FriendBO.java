package com.soapboxrace.core.bo;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.soapboxrace.core.dao.PersonaDAO;
import com.soapboxrace.core.jpa.PersonaEntity;
import com.soapboxrace.jaxb.xmpp.XMPP_FriendPersonaType;
import com.soapboxrace.xmpp.openfire.XmppFriend;

@Stateless
public class FriendBO {

	@EJB
	private PersonaDAO personaDao;

	public void sendFriendRequest(Long personaId, String displayName, String reqMessage) {
		PersonaEntity personaEntity = personaDao.findById(personaId);
		PersonaEntity personaInvite = personaDao.findByName(displayName);
		
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
