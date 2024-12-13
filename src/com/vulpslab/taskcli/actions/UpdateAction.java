package com.vulpslab.taskcli.actions;

import java.util.ArrayList;
import java.util.List;

import com.vulpslab.taskcli.models.Task;
import com.vulpslab.taskcli.services.TaskService;
import com.vulpslab.taskcli.util.Msg;

public class UpdateAction extends Action{

	private List<Validator> validators;

	public UpdateAction(){
		this.name = "update";
		validators = new ArrayList<>();
	
		Validator numberArgsValidator = (args) -> {
				if(args.length != 3){
					return new Msg(Msg.Type.ERROR, "Invalid args numbers");
				}

				return null;	
		};

		validators.add(numberArgsValidator);

		Validator typeValidator = (args) -> {
			try {
				long l = Long.parseLong(args[1]);
			} catch(NumberFormatException e){
				return new Msg(Msg.Type.ERROR, "The ID must be an integer");
			}
			return null;
		}; 

		validators.add(typeValidator);

		Validator emptyDescriptionValidator = (args)->{
			if(args[2].trim().length() == 0){
				return new Msg(Msg.Type.ERROR, "Description cannot be empty");
			}
			return null;
		};

		validators.add(emptyDescriptionValidator);
	}

	@Override
	public String getArgsExample() {
			return "update <id> <new task description>";
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
		Task t = new Task();
		t.setId(Long.parseLong(args[1]));
		t.setDescription(args[2]);

		if(new TaskService().save(t) == -1){
			return new Msg(Msg.Type.ERROR, "Task not found");
		}
		
		return new Msg(Msg.Type.SUCCESS, "Task updatded successfully (ID: "  + t.getId() + ")");
	}

} 
