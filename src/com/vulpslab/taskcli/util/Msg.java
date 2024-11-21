package com.vulpslab.taskcli.util;

public class Msg{
	public static enum Type{ ERROR, SUCCESS }

	private Type type;
	private String message;

	public Msg(Type type, String message){
		this.type = type;
		this.message = message;
	}

	public Type getType(){
		return this.type;
	}

	public String getMessage(){
		return this.message;
	}
} 
