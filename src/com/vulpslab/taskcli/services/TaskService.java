package com.vulpslab.taskcli.services;

import com.vulpslab.taskcli.daos.TxtTaskDao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.vulpslab.taskcli.daos.TaskDao;
import com.vulpslab.taskcli.models.Task;
import com.vulpslab.taskcli.util.Msg;

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
	
	public Msg markInProgress(Task task){
		Task t = dao.findById(task.getId());
		if(t == null){
			return new Msg(Msg.Type.ERROR, "Task not found");
		} else if(!t.getStatus().equals(Task.Status.todo)) {
			return new Msg(Msg.Type.ERROR, "Task must be in 'todo' status");
		} else {
			t.setStatus(Task.Status.inprogress);
			t.setUpdatedAt(new Date());
			dao.update(t);
			return new Msg(Msg.Type.SUCCESS, "Task marked successfully (ID: "  + t.getId() + ")");
		}
	}
		
	public Msg markDone(Task task){
		Task t = dao.findById(task.getId());
		if(t == null){
			return new Msg(Msg.Type.ERROR, "Task not found");
		} else if(!t.getStatus().equals(Task.Status.inprogress)) {
			return new Msg(Msg.Type.ERROR, "Task must be in 'in-progress' status");
		} else {
			t.setStatus(Task.Status.done);
			t.setUpdatedAt(new Date());
			dao.update(t);
			return new Msg(Msg.Type.SUCCESS, "Task marked successfully (ID: "  + t.getId() + ")");
		}
	}

	public List<Task> listByStatus(String status){
		List<Task> tasks = dao.findAll();
		List<Task> tks = new ArrayList <>();
		for(Task t : tasks){
			if(t.getStatus().toString().equals(status)){
				tks.add(t);
			}
		}
		return tks;
	} 

	public List<Task> list(){
		List<Task> tasks = dao.findAll();
		return tasks;
	} 
}
