package com.vulpslab.taskcli.daos;

import java.util.List;

import com.vulpslab.taskcli.models.Task;

public class JsonTaskDao implements TaskDao{
	
	@Override
	public long create(Task task) {
			return 0;
	}

	@Override
	public void update(Task task) {
	}

	@Override
	public void delete(long id) {
			
	}

	@Override
	public Task findById(long id) {
			return null;
	}

	@Override
	public List<Task> findAll() {
			return null;
	}
} 
