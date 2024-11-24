package com.vulpslab.taskcli.actions;

import com.vulpslab.taskcli.models.Task;
import com.vulpslab.taskcli.services.TaskService;
import com.vulpslab.taskcli.util.Msg;

public class AddAction extends Action{

	public AddAction(){
		this.name = "add";
	}

	@Override
	public String getArgsExample() {
			return "add <task description>";
	}

	@Override
	public Msg run(String[] args) {
		if(args.length != 2 ){
			return new Msg(Msg.Type.ERROR, "Description is required");
		} else if(args[1].trim().length() == 0){
			return new Msg(Msg.Type.ERROR, "Description cannot be empty");
		}
		Task t = new Task(args[1]);
		new TaskService().save(t);
		return new Msg(Msg.Type.SUCCESS, "Task added successfully (ID: "  + t.getId() + ")");
	}
} 
