package com.vulpslab.taskcli.actions;

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
			return new Msg(Msg.Type.SUCCESS, "Success");
	}
} 
