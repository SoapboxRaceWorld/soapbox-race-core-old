package com.soapboxrace.core.bo;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.soapboxrace.core.dao.PersonaDAO;
import com.soapboxrace.core.dao.UserDAO;
import com.soapboxrace.core.jpa.UserEntity;

@Stateless
public class AdminBO {
	
	@EJB
	private SocialBO bo;
	
	@EJB
	private TokenSessionBO tokenSessionBo;
	
	@EJB
	private PersonaDAO personaDao;
	
	@EJB
	private UserDAO userDao;
	
	public void sendCommand(Long personaId, Long abuserPersonaId, String command) {
		switch(command)
		{
			case "BAN":
				sendBan(abuserPersonaId);
				break;
			case "MUTE":
				sendMute(abuserPersonaId);
				break;
			case "KICK":
				sendKick(personaDao.findById(abuserPersonaId).getUser().getId());
				break;
		}
	}
	
	private void sendBan(Long personaId) {
		UserEntity userEntity = userDao.findById(personaDao.findById(personaId).getUser().getId());
		userEntity.setBan(true);
		userDao.update(userEntity);
		sendKick(userEntity.getId());
	}
	
	private void sendMute(Long personaId) {
		
	}
	
	private void sendKick(Long userId) {
		tokenSessionBo.deleteByUserId(userId);
	}
}
