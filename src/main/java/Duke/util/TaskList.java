package Duke.util;

import java.util.ArrayList;

import Duke.task.Task;

/**
 * Represents a list of tasks.
 */
public class TaskList {
	ArrayList<Task> tasks;
	int size;
	int count;

	/**
	 * Constructor to create a list of tasks with an initial capacity.
	 *
	 * @param size Size of TaskList.
	 */
	public TaskList(int size) {
		this.tasks = new ArrayList<>(100);
		this.size = size;
		this.count = 0;
	}

	/**
	 * Adds a task into list of tasks.
	 *
	 * @param task List of tasks.
	 */
	public void add(Task task) {
		this.tasks.add(task);
		this.count++;
	}

	/**
	 * Deletes a task from list of tasks.
	 *
	 * @param taskNum 0-based index task number to be deleted.
	 */
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

	/**
	 * Returns the tasks at given index.
	 *
	 * @param index 0-based index of task number.
	 * @return Task at given index.
	 */
	public Task getTask(int index) {
		return tasks.get(index);
	}

	/**
	 * Returns the number of tasks in the list.
	 *
	 * @return Number of tasks in the list.
	 */
	public int getCount() {
		return this.count;
	}

	/**
	 * Returns the current list of tasks as an ArrayList.
	 *
	 * @return ArrayList of current list of tasks.
	 */
	public ArrayList<Task> getCurrentList() {
		return this.tasks;
	}
}
