package com.soapboxrace.core.jpa;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "FRIEND_LIST")
@NamedQueries({ //
	@NamedQuery(name = "FriendListEntity.findByOwnerId", query = "SELECT obj FROM FriendListEntity obj WHERE obj.userOwnerId = :userOwnerId"), //
	@NamedQuery(name = "FriendListEntity.findByOwnerIdAndFriendPersona", query = "SELECT obj FROM FriendListEntity obj WHERE obj.userOwnerId = :userOwnerId AND obj.personaId = :personaId") //
})
public class FriendListEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Long userOwnerId;
	private Long personaId;
	private Long userId;
	private Boolean  isAccepted;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserOwnerId() {
		return userOwnerId;
	}

	public void setUserOwnerId(Long userOwnerId) {
		this.userOwnerId = userOwnerId;
	}

    public Long getPersonaId() {
        return personaId;
    }

    public void setPersonaId(Long personaId) {
        this.personaId = personaId;
    }
    
    public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public Boolean getIsAccepted() {
		return isAccepted;
	}
	
	public void setIsAccepted(Boolean isAccepted) {
		this.isAccepted = isAccepted;
	}

}
