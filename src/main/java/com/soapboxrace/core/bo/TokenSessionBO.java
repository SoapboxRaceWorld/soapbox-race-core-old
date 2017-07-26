package com.soapboxrace.core.bo;

import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.NotAuthorizedException;

import com.soapboxrace.core.api.util.UUIDGen;
import com.soapboxrace.core.dao.TokenSessionDAO;
import com.soapboxrace.core.dao.UserDAO;
import com.soapboxrace.core.jpa.TokenSessionEntity;
import com.soapboxrace.core.jpa.UserEntity;
import com.soapboxrace.jaxb.login.LoginStatusVO;

@Stateless
public class TokenSessionBO {

	@EJB
	private TokenSessionDAO tokenDAO;

	@EJB
	private UserDAO userDAO;

	public boolean verifyToken(Long userId, String securityToken) {
		TokenSessionEntity tokenSessionEntity = tokenDAO.findById(securityToken);
		if (tokenSessionEntity == null || !tokenSessionEntity.getUserId().equals(userId)) {
			return false;
		}
		long time = new Date().getTime();
		long tokenTime = tokenSessionEntity.getExpirationDate().getTime();
		if (time > tokenTime) {
			return false;
		}
		tokenSessionEntity.setExpirationDate(getMinutes(3));
		tokenDAO.update(tokenSessionEntity);
		return true;
	}

	public void updateToken(String securityToken) {
		TokenSessionEntity tokenSessionEntity = tokenDAO.findById(securityToken);
		Date expirationDate = getMinutes(3);
		tokenSessionEntity.setExpirationDate(expirationDate);
		tokenDAO.update(tokenSessionEntity);
	}

	public String createToken(Long userId) {
		Date expirationDate = getMinutes(15);
		String randomUUID = UUIDGen.getRandomUUID();
		UserEntity userEntity = userDAO.findById(userId);
		
		TokenSessionEntity tokenSessionEntity = new TokenSessionEntity();
		tokenSessionEntity.setExpirationDate(expirationDate);
		tokenSessionEntity.setSecurityToken(randomUUID);
		tokenSessionEntity.setUserId(userId);
		tokenSessionEntity.setPremium(userEntity.isPremium());
		tokenSessionEntity.setAdmin(userEntity.isAdmin());
		tokenDAO.insert(tokenSessionEntity);
		return randomUUID;
	}

	public boolean verifyPersona(String securityToken, Long personaId) {
		TokenSessionEntity tokenSession = tokenDAO.findById(securityToken);
		if (tokenSession == null) {
			throw new NotAuthorizedException("Invalid session...");
		}

		UserEntity user = userDAO.findById(tokenSession.getUserId());
		if (!user.ownsPersona(personaId)) {
			throw new NotAuthorizedException("Persona is not owned by user");
		}
		return true;
	}

	public void deleteByUserId(Long userId) {
		tokenDAO.deleteByUserId(userId);
	}

	private Date getMinutes(int minutes) {
		long time = new Date().getTime();
		time = time + (minutes * 60000);
		return new Date(time);
	}

	public LoginStatusVO login(String email, String password) {
		LoginStatusVO loginStatusVO = new LoginStatusVO(0L, "", false);
		if(email != null && !email.isEmpty() && password != null && !password.isEmpty()) {
			UserEntity userEntity = userDAO.findByEmail(email);
			if(userEntity != null)
			{
				if(userEntity.isBan()) {
					loginStatusVO.setDescription("BANNED ACCOUNT");
					return loginStatusVO;
				} else if(password.equals(userEntity.getPassword())) {
					Long userId = userEntity.getId();
					deleteByUserId(userId);
					String randomUUID = createToken(userId);
					loginStatusVO = new LoginStatusVO(userId, randomUUID, true);
					return loginStatusVO;
				}
			}
		}
		loginStatusVO.setDescription("LOGIN ERROR");
		return loginStatusVO;
	}

	public Long getActivePersonaId(String securityToken) {
		TokenSessionEntity tokenSessionEntity = tokenDAO.findById(securityToken);
		return tokenSessionEntity.getActivePersonaId();
	}

	public void setActivePersonaId(String securityToken, Long personaId, Boolean isLogout) {
		TokenSessionEntity tokenSessionEntity = tokenDAO.findById(securityToken);

		if (!isLogout && !userDAO.findById(tokenSessionEntity.getUserId()).ownsPersona(personaId)) {
			throw new NotAuthorizedException("Persona not owned by user");
		}

		tokenSessionEntity.setActivePersonaId(personaId);
		tokenDAO.update(tokenSessionEntity);
	}

	public String getActiveRelayCryptoTicket(String securityToken) {
		TokenSessionEntity tokenSessionEntity = tokenDAO.findById(securityToken);
		return tokenSessionEntity.getRelayCryptoTicket();
	}

	public Long getActiveLobbyId(String securityToken) {
		TokenSessionEntity tokenSessionEntity = tokenDAO.findById(securityToken);
		return tokenSessionEntity.getActiveLobbyId();
	}

	public void setActiveLobbyId(String securityToken, Long lobbyId) {
		TokenSessionEntity tokenSessionEntity = tokenDAO.findById(securityToken);
		tokenSessionEntity.setActiveLobbyId(lobbyId);
		tokenDAO.update(tokenSessionEntity);
	}

	public boolean isPremium(String securityToken) {
		TokenSessionEntity tokenSessionEntity = tokenDAO.findById(securityToken);
		return tokenSessionEntity.isPremium();
	}

	public boolean isAdmin(String securityToken) {
		TokenSessionEntity tokenSessionEntity = tokenDAO.findById(securityToken);
		return tokenSessionEntity.isAdmin();
	}

}
