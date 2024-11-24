package com.vulpslab.taskcli.services;

import com.vulpslab.taskcli.daos.TxtTaskDao;
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
			dao.update(task);
			return task.getId();
		}
	}

}
