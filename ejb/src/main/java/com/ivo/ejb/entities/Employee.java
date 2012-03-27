package com.ivo.ejb.entities; 

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the employee database table.
 * 
 */
@Entity
@Table(name="employee")
public class Employee implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@TableGenerator(name = "EMPLOYEE_GEN", table = "PRIMARY_KEY_SEQUENCES", pkColumnName = "TABLE_NAME", valueColumnName = "CURRENT_PK_VALUE", pkColumnValue = "EMPLOYEE")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "EMPLOYEE_GEN")
	private Long id;

	@Column(nullable=false, length=50)
	private String name;

    public Employee() {
    }

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}