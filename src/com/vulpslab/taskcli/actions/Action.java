package com.vulpslab.taskcli.actions;

import com.vulpslab.taskcli.util.Msg;

public abstract class Action{
	protected String name;
	public abstract String getArgsExample();
	public abstract Msg run(String[] args);

	public String getName(){
		return this.name;
	}
} 
