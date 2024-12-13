package com.vulpslab.taskcli.actions;

import java.util.ArrayList;
import java.util.List;

import com.vulpslab.taskcli.models.Task;
import com.vulpslab.taskcli.services.TaskService;
import com.vulpslab.taskcli.util.Msg;

public class AddAction extends Action{

	private List<Validator> validators;

	public AddAction(){
		this.name = "add";
		validators = new ArrayList<>();
	
		Validator numberArgsValidator = (args) -> {
				if(args.length != 2){
					return new Msg(Msg.Type.ERROR, "Invalid args numbers");
				}

				return null;	
		};

		validators.add(numberArgsValidator);

		Validator emptyDescriptionValidator = (args)->{
			if(args[1].trim().length() == 0){
				return new Msg(Msg.Type.ERROR, "Description cannot be empty");
			}
			return null;
		};

		validators.add(emptyDescriptionValidator);
	}

	@Override
	public String getArgsExample() {
			return "add <task description>";
	}

	@Override
	public Msg run(String[] args) {
		Msg msg = null;

		for(Validator v : validators){
			msg = v.validate(args);
			if(msg != null) {
				return msg;
			}
		}

		Task t = new Task(args[1]);
		new TaskService().save(t);
		return new Msg(Msg.Type.SUCCESS, "Task added successfully (ID: "  + t.getId() + ")");
	}
} 
