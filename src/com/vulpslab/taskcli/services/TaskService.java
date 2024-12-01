package com.vulpslab.taskcli.services;

import com.vulpslab.taskcli.daos.TxtTaskDao;

import java.util.Date;

import com.vulpslab.taskcli.daos.TaskDao;
import com.vulpslab.taskcli.models.Task;

public class TaskService {
	private TaskDao dao;

	public TaskService(){
			dao = new TxtTaskDao();
	} 

	public long save(Task task){
		if(task.getId() < 1){
			return dao.create(task);
		} else {
			Task t = dao.findById(task.getId());
			if(t != null){
				t.setDescription(task.getDescription());
				t.setUpdatedAt(new Date());
				dao.update(t);
				return task.getId();
			} else {
				return -1;
			}
		}
	}

	public boolean delete(long id) {
		Task t = dao.findById(id);
		if(t == null){
			return false;
		} else {
			dao.delete(id);
			return true;
		}
	}
	

}
