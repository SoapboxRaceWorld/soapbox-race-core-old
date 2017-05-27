package com.soapboxrace.core.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
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
	@Column(name = "ID", nullable = false)
	private Long userOwnerId;
	
	private Long personaId;
	private Long userId;
	private Boolean  isAccepted;

	public Long getUserOwnerId() {
		return userOwnerId;
	}

	public void setUserOwnerId(Long value) {
		this.userOwnerId = value;
	}

    public Long getPersonaId() {
        return personaId;
    }

    public void setPersonaId(Long value) {
        this.personaId = value;
    }
    
    public Long getUserId() {
		return userId;
	}

	public void setUserId(Long value) {
		this.userId = value;
	}
	
	public Boolean getIsAccepted() {
		return isAccepted;
	}
	
	public void setIsAccepted(Boolean value) {
		this.isAccepted = value;
	}

}
