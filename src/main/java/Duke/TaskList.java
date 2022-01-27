package Duke;

import java.util.ArrayList;

import Duke.task.Task;

public class TaskList {
	ArrayList<Task> tasks;
	int count;

	public TaskList(ArrayList<Task> tasks) {
		this.tasks = tasks;
		this.count = tasks.size();
	}

	public void add(Task task) {
		this.tasks.add(task);
		this.count++;
	}

	public void delete(int taskNum) {
		try {
			this.tasks.remove(taskNum);
			this.count--;
		} catch (IndexOutOfBoundsException e) {
			System.out.println("\t____________________________________________________________");
			System.out.println("\t☹ Woof Woof!!! This task cannot be found with my Wonka eyes!!!");
			System.out.println("\t____________________________________________________________");
		}
	}

	public Task getTask(int index) {
		return tasks.get(index);
	}

	public int getCount() {
		return this.count;
	}
}
