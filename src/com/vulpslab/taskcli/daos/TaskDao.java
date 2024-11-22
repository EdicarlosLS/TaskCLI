package com.vulpslab.taskcli.daos;

import java.util.List;

import com.vulpslab.taskcli.models.Task;

public interface TaskDao {
	public abstract long create(Task task);
	public abstract void update(Task task);
	public abstract void delete(long id);
	public abstract Task findById(long id);
	public abstract List<Task> findAll();
} 
