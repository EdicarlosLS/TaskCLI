package com.vulpslab.taskcli.models;

import java.util.Date;

public class Task{
	public enum Status { todo, inprogress, done };

	private long id;
	private String description;
	private Status status;
	private Date createdAt;
	private Date updatedAt;

	public Task(){}

	public Task(String description){
		this.setId(0);
		this.setDescription(description);
		this.setStatus(Status.todo);
		this.setCreatedAt(new Date());
		this.setUpdatedAt(new Date());
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public void setStatus(String status){
		for (Status s : Status.values()) {
			if(s.toString().equalsIgnoreCase(status)){
				this.status = s;
				break;
			}
		}
	}

	public Status getStatus() {
		return status;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

}
