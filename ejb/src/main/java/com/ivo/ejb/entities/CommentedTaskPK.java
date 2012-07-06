package com.ivo.ejb.entities;



import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * The primary key class for the commented_tasks database table.
 * 
 */
@Embeddable
public class CommentedTaskPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	
	@Basic(optional = false)
    @NotNull
    @Column(name = "taskid", nullable=false)
	private Integer taskId;
	
    @Basic(optional = false)
    @NotNull
    @Column(name = "commentid", nullable=false)
	private Integer commentId;

    public CommentedTaskPK() {
    }

	public Integer getTaskId() {
		return taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

	public Integer getCommentId() {
		return commentId;
	}

	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof CommentedTaskPK)) {
			return false;
		}
		CommentedTaskPK castOther = (CommentedTaskPK)other;
		return 
			this.taskId.equals(castOther.taskId)
			&& this.commentId.equals(castOther.commentId);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.taskId.hashCode();
		hash = hash * prime + this.commentId.hashCode();
		
		return hash;
    }
}