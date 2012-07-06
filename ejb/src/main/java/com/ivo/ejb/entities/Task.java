package com.ivo.ejb.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.Date;

/**
 * The persistent class for the task database table.
 *
 */
@Entity
@Table(name = "task")
@NamedQueries({
    @NamedQuery(name = "Task.findAll", query = "SELECT t FROM Task t"),
    @NamedQuery(name = "Task.findById", query = "SELECT t FROM Task t WHERE t.id = :id"),
    @NamedQuery(name = "Task.findByTitle", query = "SELECT t FROM Task t WHERE t.title = :title"),
    @NamedQuery(name = "Task.findByNumber", query = "SELECT t FROM Task t WHERE t.number = :number"),
    @NamedQuery(name = "Task.findByDescription", query = "SELECT t FROM Task t WHERE t.description = :description"),
    @NamedQuery(name = "Task.findByDatecreated", query = "SELECT t FROM Task t WHERE t.dateCreated = :datecreated"),
    @NamedQuery(name = "Task.findByDatefinished", query = "SELECT t FROM Task t WHERE t.dateFinished = :datefinished"),
    @NamedQuery(name = "Task.findByEstimation", query = "SELECT t FROM Task t WHERE t.estimation = :estimation"),
    @NamedQuery(name = "Task.findByUser", query = "SELECT t FROM Task t WHERE t.employee.userName = :userName ORDER BY t.taskPriority.level, t.dateCreated")})
public class Task implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    @TableGenerator(name = "TASK_GEN", table = "PRIMARY_KEY_SEQUENCES", pkColumnName = "TABLE_NAME", valueColumnName = "CURRENT_PK_VALUE", pkColumnValue = "TASK")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "TASK_GEN")
    private Integer id;
    
    @NotNull
    @Column(name = "datecreated")
    @Temporal(TemporalType.DATE)
    private Date dateCreated;
    
    @Column(name = "datefinished")
    @Temporal(TemporalType.DATE)
    private Date dateFinished;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "description")
    private String description;
    
    @Column(name = "estimation")
    private Integer estimation;
    
    @Basic(optional = false)    
    @Column(name = "number")
    private Integer number;
    
    @Basic(optional = false)    
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "title")
    private String title;
    
    @JoinColumn(name = "assignedemployee", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Employee employee;
    
    @JoinColumn(name = "taskpriorityid", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private TaskPriority taskPriority;
    
    @JoinColumn(name = "taskstatusid", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private TaskStatus taskStatus;

    public Task() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDateCreated() {
        return this.dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateFinished() {
        return this.dateFinished;
    }

    public void setDateFinished(Date dateFinished) {
        this.dateFinished = dateFinished;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getEstimation() {
        return this.estimation;
    }

    public void setEstimation(Integer estimation) {
        this.estimation = estimation;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Employee getEmployee() {
        return this.employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public TaskPriority getTaskPriority() {
        return taskPriority;
    }

    public void setTaskPriority(TaskPriority taskPriority) {
        this.taskPriority = taskPriority;
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }

    @Override
    public String toString() {
        return "Task{" + "dateCreated=" + dateCreated + ", dateFinished=" + dateFinished + ", description=" + description + ", estimation=" + estimation + ", number=" + number + ", title=" + title + ", employee=" + employee + ", taskPriority=" + taskPriority + ", taskStatus=" + taskStatus + '}';
    }        
}