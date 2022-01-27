package tasks;

import java.util.ArrayList;

/**
 * class which stores todo, event and deadline tasks as a list
 */
public class TaskList {
	private ArrayList<Task> TASKLIST;

	/**
	 * Constructor for Tasklist based on Arraylist
	 */
	public TaskList(){
		this.TASKLIST = new ArrayList<>();
	}

	/**
	 * Getter function for size of list
	 * @return size of the list
	 */
	public int size() {
		return TASKLIST.size();
	}

	/**
	 * Adds the task to list
	 * @param task to be added to list
	 */
	public void add(Task task) {
		TASKLIST.add(task);
	}

	/**
	 * Removes the task from the index
	 * @param index to remove from zero-indexed
	 */
	public void remove(int index){
		TASKLIST.remove(index);
	}

	/**
	 * Gets the task from the index and returns it
	 * @param index of the list to get from zero-indexed
	 * @return task located at the index
	 */
	public Task get(int index) {
		return TASKLIST.get(index);
	}

	/**
	 * Replaces the task at the index with the new task
	 * @param index of the task to be replaced
	 * @param task to replace at index
	 */
	public void set(int index, Task task){
		TASKLIST.set(index, task);
	}
}
