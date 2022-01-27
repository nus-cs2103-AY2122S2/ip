package main.java.duke;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskArr;
    private int currTask;

    /** Constructs an instance of Tasklist. */
    public TaskList() {
        this.taskArr = new ArrayList<Task>();
        currTask = 0;
    }

    /**
     * Constructs an instance of Tasklist.
     *
     * @param t Tasks stored in an arraylist to be converted to Tasklist.
     */
    public TaskList(ArrayList<Task> t) {
        this.taskArr = t;
        this.currTask = t.size();
    }

    /**
     * Marks a task as done.
     *
     * @param num Position of task in Tasklist to be marked as done.
     */
    public void mark(int num) {
        taskArr.get(num).markDone();
    }

    /**
     * Marks a task as undone.
     *
     * @param num Position of task in Tasklist to be marked as undone.
     */
    public void unmark(int num) {
        taskArr.get(num).markUndone();
    }


    public ArrayList<Task> getTaskArr() {
        return this.taskArr;
    }

    /**
     * Adds a task to the Tasklist.
     *
     * @param t Task to be added to the Tasklist.
     */
    public void addTask(Task t) {
        taskArr.add(t);
        currTask++;
    }

    /**
     * Deletes a task to the Tasklist.
     *
     * @param taskNum Position of task in Tasklist to be deleted.
     */
    public void delete(int taskNum) {
        taskArr.remove(taskNum);
        currTask--;
    }

    public int size() {
        return taskArr.size();
    }

    /**
     * Gets a specific task from the Tasklist.
     *
     * @param num Position of task in Tasklist to be retrieved.
     */
    public Task getTask(int num) {
        return taskArr.get(num);
    }
}
