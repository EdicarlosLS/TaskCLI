package com.vulpslab.taskcli.actions;

import java.util.ArrayList;
import java.util.List;

import com.vulpslab.taskcli.models.Task;
import com.vulpslab.taskcli.services.TaskService;
import com.vulpslab.taskcli.util.Msg;

public class ListAction extends Action{

	private List<Validator> validators;

	public ListAction(){
		this.name = "list";
		validators = new ArrayList<>();
		
		Validator numberArgsValidator = (args) -> {
				if(args.length > 2){
					return new Msg(Msg.Type.ERROR, "Invalid args numbers");
				}

				return null;	
		};

		validators.add(numberArgsValidator);

		Validator typeStatusValidator = (args) -> {
			if(args.length == 2){
				String status = args[1];

				if(status.equals("todo") || status.equals("inprogress") || status.equals("done")){
					return null;
				} else {
					return new Msg(Msg.Type.ERROR, "Invalid status type");
				}
			}
			
			return null;
		}; 

		validators.add(typeStatusValidator);

	}

	@Override
	public String getArgsExample() {
			return "list\n\t\tlist todo\n\t\tlist in-progress\n\t\tlist done";
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

		List<Task> tasks;
		TaskService service = new TaskService();
		if(args.length == 2){
			tasks = service.listByStatus(args[1]);
		} else{
			tasks = service.list();
		}

		return new Msg(Msg.Type.SUCCESS, showTasks(tasks));
	}

	private String showTasks(List<Task> tasks){
		StringBuilder sb = new StringBuilder("\n");
		for(Task t : tasks){
			sb.append("Id : ").append(t.getId()).append(" | ")
				.append("Status : ").append(t.getStatus().toString()).append(" | ")
				.append("Description : ").append(t.getDescription()).append("\n");
		}
		return sb.toString();
	}

} 
