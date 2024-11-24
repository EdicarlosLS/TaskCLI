package com.vulpslab.taskcli.daos;

import java.io.File;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.vulpslab.taskcli.models.Task;

public class TxtTaskDao implements TaskDao{
	private long lastInsertId;
	private File file;
	private Scanner sc;
	private PrintStream out;

	private String separator = "_-_";
	private final int ID = 0;
	private final int DESCRIPITION = 1;
	private final int STATUS = 2;
	private final int CREATED_AT = 3;
	private final int UPDATED_AT = 4;

	private SimpleDateFormat formatter;

	private List<Task> tasks;

	public TxtTaskDao(){
		formatter = new SimpleDateFormat("dd/MM/yyyy H:mm");
		try {
			file = new File("tasks.txt");
			
			if(file.createNewFile()){
				lastInsertId = 0;	
			}

			sc = new Scanner(file);
			loadTasksList();
			out = new PrintStream(file);

		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	@Override
	public long create(Task task) {
		task.setId(lastInsertId + 1);
		tasks.add(task);

		for (Task t : tasks) {
			out.println(encode(t));
		}
		return task.getId();
	}

	@Override
	public void update(Task task){
	}

	@Override
	public void delete(long id) {
			
	}

	@Override
	public Task findById(long id) {
			return null;
	}

	@Override
	public List<Task> findAll() {
			return null;
	}

	private String encode(Task task){
		StringBuilder sb = new StringBuilder("")
		.append(task.getId()).append(separator)
		.append(task.getDescription()).append(separator)
		.append(task.getStatus()).append(separator)
		.append(formatter.format(task.getCreatedAt())).append(separator)
		.append(formatter.format(task.getUpdatedAt()));

		return sb.toString();
	}

	private void loadTasksList(){
		this.tasks = new ArrayList<>();
		
		while(sc.hasNext()){
			Task t = new Task();
			String line = sc.nextLine();
			String[] values = line.split(separator);
			
			t.setId(Long.parseLong(values[ID]));
			t.setDescription(values[DESCRIPITION]);
			t.setStatus(values[STATUS]);
		
			try{
				String dateInString = values[CREATED_AT];
				Date date = formatter.parse(dateInString);

				t.setCreatedAt(date);

				dateInString = values[UPDATED_AT];
				date = formatter.parse(dateInString);

				t.setUpdatedAt(date);
				
			} catch(Exception e){
				System.out.println(e);
			}

			if(t.getId() > lastInsertId){
				lastInsertId = t.getId();
			}
			tasks.add(t);
		}
	}
} 
