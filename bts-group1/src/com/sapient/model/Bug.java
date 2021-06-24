package com.sapient.model;

import java.util.*;

public class Bug {

	private int id;
	private int project_id;
	private String name;
	private String description;
	private String email;
	private String owner;
	private Priority priority;
	private Date created_date;
	private Date completed_date;
	private Status status;
	private Bugtype bugtype;

	public Bug(int project_id2, String name2, String description2, String email2, String owner2, String priority2,
			String status2, String bugtype2) {

		project_id = project_id2;
		name = name2;

		description = description2;
		email = email2;
		owner = owner2;
		priority = Enum.valueOf(Priority.class, priority2);
		status = Enum.valueOf(Status.class, status2);
		bugtype = Enum.valueOf(Bugtype.class, bugtype2);

	}

	public Bug(int id2, int project_id2, String name2, String description2, String email2, String owner2,
			String priority2, String status2, String bugtype2) {

		id = id2;
		project_id = project_id2;
		name = name2;
		description = description2;
		email = email2;
		owner = owner2;
		priority = Enum.valueOf(Priority.class, priority2);
		status = Enum.valueOf(Status.class, status2);
		bugtype = Enum.valueOf(Bugtype.class, bugtype2);

	}
	
	public Bug() {
		
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	public Date getCreated_date() {
		return created_date;
	}

	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}

	public Date getCompleted_date() {
		return completed_date;
	}

	public void setCompleted_date(Date completed_date) {
		this.completed_date = completed_date;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProject_id() {
		return project_id;
	}

	public void setProject_id(int project_id) {
		this.project_id = project_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Bugtype getBugtype() {
		return bugtype;
	}

	public void setBugtype(Bugtype bugtype) {
		this.bugtype = bugtype;
	}

}
