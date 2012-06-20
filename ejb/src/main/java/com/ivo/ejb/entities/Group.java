package com.ivo.ejb.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the grouptable database table.
 * 
 */
@Entity
@Table(name="grouptable")
public class Group implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private GroupPK id;

	//bi-directional many-to-one association to Users
    @ManyToOne
	@JoinColumn(name="userid", nullable=false, insertable=false, updatable=false)
	private User user;

    public Group() {
    }

	public GroupPK getId() {
		return this.id;
	}

	public void setId(GroupPK id) {
		this.id = id;
	}
	
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}