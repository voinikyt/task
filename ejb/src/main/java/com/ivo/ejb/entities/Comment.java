package com.ivo.ejb.entities;



import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


/**
 * The persistent class for the comment database table.
 * 
 */
@Entity
@Table(name="comment")
@NamedQueries({
    @NamedQuery(name = "Comment.findAll", query = "SELECT c FROM Comment c"),
    @NamedQuery(name = "Comment.findById", query = "SELECT c FROM Comment c WHERE c.id = :id"),
    @NamedQuery(name = "Comment.findByDate", query = "SELECT c FROM Comment c WHERE c.date = :date")})
public class Comment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
        @Basic(optional = false)
        @NotNull    	
	@Column(name = "id", unique=true, nullable=false)
        @TableGenerator(name = "COMMENT_GEN", table = "PRIMARY_KEY_SEQUENCES", pkColumnName = "TABLE_NAME", valueColumnName = "CURRENT_PK_VALUE", pkColumnValue = "COMMENT")
        @GeneratedValue(strategy = GenerationType.TABLE, generator = "COMMENT_GEN")
	private Integer id;

	@Basic(optional = false)
    @NotNull
    @Temporal( TemporalType.DATE)
	@Column(nullable=false)
	private Date date;

    @ManyToOne	
    @JoinColumn(name = "employeeid", referencedColumnName = "id")
	private Employee employee;

    public Comment() {
    }

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Employee getEmployee() {
		return this.employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
}