package tasks;

import java.util.ArrayList;

public class TaskList {
	private ArrayList<Task> TASKLIST;

	public TaskList(){
		this.TASKLIST = new ArrayList<>();
	}

	public int size() {
		return TASKLIST.size();
	}

	public void add(Task task) {
		TASKLIST.add(task);
	}

	public void remove(int index){
		TASKLIST.remove(index);
	}

	public Task get(int index) {
		return TASKLIST.get(index);
	}

	public void set(int index, Task task){
		TASKLIST.set(index, task);
	}
}
