package com.vulpslab.taskcli.actions;

import java.util.ArrayList;
import java.util.List;

import com.vulpslab.taskcli.models.Task;
import com.vulpslab.taskcli.services.TaskService;
import com.vulpslab.taskcli.util.Msg;

public class MarkInProgressAction extends Action{

	private List<Validator> validators;

	public MarkInProgressAction(){
		this.name = "mark-in-progress";
		validators = new ArrayList<>();
		
		Validator numberArgsValidator = (args) -> {
				if(args.length != 2){
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

	}

	@Override
	public String getArgsExample() {
			return "mark-in-progress <id>";
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

		return new TaskService().markInProgress(t);
	}

	@FunctionalInterface
	private interface Validator{
		
		public abstract Msg validate(String[] args);
	}
} 
