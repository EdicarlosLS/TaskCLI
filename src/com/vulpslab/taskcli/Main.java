package com.vulpslab.taskcli;

import com.vulpslab.taskcli.actions.Action;
import com.vulpslab.taskcli.actions.AddAction;
import com.vulpslab.taskcli.util.Msg;

import java.util.HashMap;

public class Main{
	private static HashMap<String, Action> map = new HashMap<>();

	private static void putAction(Action action){
		map.put(action.getName(), action);
	}

	public static void main(String[] args) {
		putAction(new AddAction());

		if(args.length > 0){
		
			String actionSelected = args[0];

			Action action = map.get(actionSelected);
			if(action == null){
				tryThis();
			} else{
				Msg returnedMessage = action.run(args);
				System.out.println(returnedMessage.getMessage());
			}
		} else {
			tryThis();
		}
	}

	privat static void tryThis(){
	System.out.println("Invalid Action. Try this: \n");

			for (Action action : map.values()) {
				System.out.println("\t" + action.getArgsExample());
			}
	}
}
