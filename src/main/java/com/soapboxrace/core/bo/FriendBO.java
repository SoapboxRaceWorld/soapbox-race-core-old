package com.soapboxrace.core.bo;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.soapboxrace.core.dao.FriendListDAO;
import com.soapboxrace.core.dao.PersonaDAO;
import com.soapboxrace.core.jpa.FriendListEntity;
import com.soapboxrace.core.jpa.PersonaEntity;
import com.soapboxrace.jaxb.http.ArrayOfFriendPersona;
import com.soapboxrace.jaxb.http.FriendPersona;
import com.soapboxrace.jaxb.http.PersonaFriendsList;
import com.soapboxrace.jaxb.xmpp.XMPP_FriendPersonaType;
import com.soapboxrace.jaxb.xmpp.XMPP_FriendResultType;
import com.soapboxrace.jaxb.xmpp.XMPP_ResponseTypeFriendResult;
import com.soapboxrace.xmpp.openfire.XmppFriend;

@Stateless
public class FriendBO {

	@EJB
	private PersonaDAO personaDao;
	
	@EJB
	private FriendListDAO friendListDao;
	
	@EJB
	private DriverPersonaBO personaBO;
	
	public PersonaFriendsList getFriendListFromUserId(Long userId) {
		ArrayOfFriendPersona arrayOfFriendPersona = new ArrayOfFriendPersona();
		
		List<FriendListEntity> friendList = friendListDao.findByOwnerId(userId);
		for(FriendListEntity entity : friendList) {
			
			PersonaEntity personaEntity = personaDao.findById(entity.getPersonaId());
			if(personaEntity == null)
				continue;
				
			FriendPersona friendPersona = new FriendPersona();
			friendPersona.setIconIndex(personaEntity.getIconIndex());
			friendPersona.setLevel(personaEntity.getLevel());
			friendPersona.setName(personaEntity.getName());
			friendPersona.setOriginalName(personaEntity.getName());
			friendPersona.setPersonaId(personaEntity.getPersonaId());
			friendPersona.setPresence(entity.getIsAccepted() ? personaBO.getPersonaPresenceByName(personaEntity.getName()).getPresence() : 3 );
			friendPersona.setSocialNetwork(1);
			friendPersona.setUserId(personaEntity.getUser().getId());
			
			arrayOfFriendPersona.getFriendPersona().add(friendPersona);
		}
		
		PersonaFriendsList personaFriendsList = new PersonaFriendsList();
		personaFriendsList.setFriendPersona(arrayOfFriendPersona);
		return personaFriendsList;
	}

	public void sendFriendRequest(Long personaId, String displayName, String reqMessage) {
		PersonaEntity personaSender = personaDao.findById(personaId);
		PersonaEntity personaInvited = personaDao.findByName(displayName);
		if(personaSender == null || personaInvited == null)
			return;
		
		if(personaSender.getPersonaId() == personaInvited.getPersonaId())
			return;
		
		FriendListEntity friendListEntity = friendListDao.findByOwnerIdAndFriendPersona(personaSender.getUser().getId(), personaInvited.getPersonaId());
		if(friendListEntity != null)
			return;
		
		XMPP_FriendPersonaType friendPersonaType = new XMPP_FriendPersonaType();
		friendPersonaType.setIconIndex(personaSender.getIconIndex());
		friendPersonaType.setLevel(personaSender.getLevel());
		friendPersonaType.setName(personaSender.getName());
		friendPersonaType.setOriginalName(personaSender.getName());
		friendPersonaType.setPersonaId(personaSender.getPersonaId());
		friendPersonaType.setPresence(3);
		friendPersonaType.setUserId(personaSender.getUser().getId());
		
		XmppFriend xmppFriend = new XmppFriend(personaInvited.getPersonaId());
		xmppFriend.sendFriendRequest(friendPersonaType);
		
		// Insert db record for invited player
		FriendListEntity friendListInsert = new FriendListEntity();
		friendListInsert.setUserOwnerId(personaInvited.getUser().getId());
		friendListInsert.setUserId(personaSender.getUser().getId());
		friendListInsert.setPersonaId(personaSender.getPersonaId());
		friendListInsert.setIsAccepted(false);
		friendListDao.insert(friendListInsert);
	}
	
	public void sendResponseFriendRequest(Long personaId, Long friendPersonaId, int resolution) {
		// Execute some DB things
		PersonaEntity personaInvited = personaDao.findById(personaId);
		PersonaEntity personaSender = personaDao.findById(friendPersonaId);
		if(personaInvited == null || personaSender == null)
			return;
		
		if(resolution == 0) {
			removeFriend(personaInvited.getPersonaId(), personaSender.getPersonaId());
			return;
		}
		
		FriendListEntity friendListEntity = friendListDao.findByOwnerIdAndFriendPersona(personaInvited.getUser().getId(), personaSender.getPersonaId());
		if(friendListEntity == null)
			return;
		
		friendListEntity.setIsAccepted(true);
		friendListDao.update(friendListEntity);
		
		// Insert db record for sender player
		friendListEntity = friendListDao.findByOwnerIdAndFriendPersona(personaSender.getUser().getId(), personaInvited.getPersonaId());
		if(friendListEntity == null) {
			FriendListEntity friendListInsert = new FriendListEntity();
			friendListInsert.setUserOwnerId(personaSender.getUser().getId());
			friendListInsert.setUserId(personaInvited.getUser().getId());
			friendListInsert.setPersonaId(personaInvited.getPersonaId());
			friendListInsert.setIsAccepted(true);
			friendListDao.insert(friendListInsert);
		}
		
		// Send all info to personaSender
		FriendPersona friendPersona = new FriendPersona();
		friendPersona.setIconIndex(personaInvited.getIconIndex());
		friendPersona.setLevel(personaInvited.getLevel());
		friendPersona.setName(personaInvited.getName());
		friendPersona.setOriginalName(personaInvited.getName());
		friendPersona.setPersonaId(personaInvited.getPersonaId());
		friendPersona.setPresence(3);
		friendPersona.setUserId(personaInvited.getUser().getId());
		
		XMPP_FriendResultType friendResultType = new XMPP_FriendResultType();
		friendResultType.setFriendPersona(friendPersona);
		friendResultType.setResult(resolution);
		
		XMPP_ResponseTypeFriendResult responseTypeFriendResult = new XMPP_ResponseTypeFriendResult();
		responseTypeFriendResult.setFriendResult(friendResultType);
		
		XmppFriend xmppFriend = new XmppFriend(personaSender.getPersonaId());
		xmppFriend.sendResponseFriendRequest(responseTypeFriendResult);
		
		// Send all info to personaInvited
		friendPersona = new FriendPersona();
		friendPersona.setIconIndex(personaSender.getIconIndex());
		friendPersona.setLevel(personaSender.getLevel());
		friendPersona.setName(personaSender.getName());
		friendPersona.setOriginalName(personaSender.getName());
		friendPersona.setPersonaId(personaSender.getPersonaId());
		friendPersona.setPresence(3);
		friendPersona.setUserId(personaSender.getUser().getId());
		
		friendResultType = new XMPP_FriendResultType();
		friendResultType.setFriendPersona(friendPersona);
		friendResultType.setResult(resolution);
		
		responseTypeFriendResult = new XMPP_ResponseTypeFriendResult();
		responseTypeFriendResult.setFriendResult(friendResultType);
		
		xmppFriend = new XmppFriend(personaInvited.getPersonaId());
		xmppFriend.sendResponseFriendRequest(responseTypeFriendResult);
	}
	
	public void removeFriend(Long personaId, Long friendPersonaId) {
		PersonaEntity personaInvite = personaDao.findById(personaId);
		PersonaEntity personaSender = personaDao.findById(friendPersonaId);
		if(personaInvite == null || personaSender == null)
			return;
		
		FriendListEntity friendListEntity = friendListDao.findByOwnerIdAndFriendPersona(personaInvite.getUser().getId(), personaSender.getPersonaId());
		if(friendListEntity != null)
			friendListDao.delete(friendListEntity);
		
		friendListEntity = friendListDao.findByOwnerIdAndFriendPersona(personaSender.getUser().getId(), personaInvite.getPersonaId());
		if(friendListEntity != null)
			friendListDao.delete(friendListEntity);
	}

}
