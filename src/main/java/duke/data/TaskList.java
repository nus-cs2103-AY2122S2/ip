package main.java.duke.data;

import main.java.duke.task.Task;

import java.util.ArrayList;

/**
 * List containing all the Task
 */
public class TaskList {
    private ArrayList<Task> taskLists;

    /**
     * Constructs a empty TaskList
     */
    public TaskList() {
        taskLists = new ArrayList<>();
    }

    /**
     * Adds a new Task into an existing TaskList
     * @param newTask
     */
    public void addTask(Task newTask) {
        taskLists.add(newTask);
    }

    /**
     * Removes a Task at a specific index
     * @param index removes item at said index
     * @return Task that was removed
     */
    public Task removeTask(int index) {
        return taskLists.remove(index);
    }

    /***
     * Gets the task at the specific index of the TaskList
     * @param index
     * @return the Task at that specific index
     */
    public Task getTask(int index) {
        return taskLists.get(index);
    }

    /***
     * @return Gets number of Task in the TaskList
     */
    public int taskLength() {
        return taskLists.size();
    }

    /***
     * @return a ArrayList of Task
     */
    public ArrayList<Task> getTaskList() {
        return this.taskLists;
    }

    /***
     * Marks the task at index to be done
     * @param index index in the task
     */
    public void markTask(int index) {
        taskLists.get(index).setMark(true);
    }
    
    /***
     * Marks the task at index to be undone
     * @param index index in the task
     */
    public void unmarkTask(int index) {
        taskLists.get(index).setMark(false);
    }
    
}
