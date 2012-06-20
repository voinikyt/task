package com.ivo.ejb.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the grouptable database table.
 * 
 */
@Embeddable
public class GroupPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(nullable=false, length=10)
	private String userid;

	@Column(name="groupid", nullable=false, length=20)
	private String roleName;

    public GroupPK() {
    }
	public String getUserid() {
		return this.userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getRoleName() {
		return this.roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof GroupPK)) {
			return false;
		}
		GroupPK castOther = (GroupPK)other;
		return 
			this.userid.equals(castOther.userid)
			&& this.roleName.equals(castOther.roleName);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.userid.hashCode();
		hash = hash * prime + this.roleName.hashCode();
		
		return hash;
    }
}