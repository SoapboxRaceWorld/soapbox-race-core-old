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
	@NamedQuery(name = "FriendListEntity.findByOwnerId", query = "SELECT obj FROM FriendListEntity obj WHERE obj.userOwnerId = :userOwnerId") //
})
public class FriendListEntity {

	@Id
	@Column(name = "ID", nullable = false)
	private long id;

	private long userOwnerId;
	private long personaId;
	private long userId;

	public long getId() {
		return id;
	}

	public void setId(long value) {
		this.id = value;
	}

	public long getUserOwnerId() {
		return userOwnerId;
	}

	public void setUserOwnerId(long value) {
		this.userOwnerId = value;
	}

    public long getPersonaId() {
        return personaId;
    }

    public void setPersonaId(long value) {
        this.personaId = value;
    }
    
    public long getUserId() {
		return userId;
	}

	public void setUserId(long value) {
		this.userId = value;
	}

}
