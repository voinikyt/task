package com.ivo.ejb.entities;



import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the commented_tasks database table.
 * 
 */
@Entity
@Table(name="commented_tasks")
@NamedQueries({
    @NamedQuery(name = "CommentedTasks.findAll", query = "SELECT c FROM CommentedTask c"),
    @NamedQuery(name = "CommentedTasks.findByTaskid", query = "SELECT c FROM CommentedTask c WHERE c.commentedTaskPK.taskId = :taskid"),
    @NamedQuery(name = "CommentedTasks.findByCommentid", query = "SELECT c FROM CommentedTask c WHERE c.commentedTaskPK.commentId = :commentid")})
public class CommentedTask implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CommentedTaskPK commentedTaskPK;

    @JoinColumn(name = "commentid", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.EAGER)
	private Comment comment;	
    

    @JoinColumn(name = "taskid", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.EAGER)
	private Task task;

    public CommentedTask() {
    }

	public CommentedTaskPK getCommentedTaskPK() {
		return commentedTaskPK;
	}


	public void setCommentedTaskPK(CommentedTaskPK commentedTaskPK) {
		this.commentedTaskPK = commentedTaskPK;
	}

	public Comment getComment() {
		return this.comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}
	
	public Task getTask() {
		return this.task;
	}

	public void setTask(Task task) {
		this.task = task;
	}
	
}