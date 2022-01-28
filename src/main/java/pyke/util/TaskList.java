package pyke.util;

import pyke.task.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<Task>();
    }

    /**
     * This method will get the total number of tasks stored in this object
     *
     * @return the total number of tasks stores here
     */
    public int getSize() {
        return taskList.size();
    }

    public boolean isEmpty() {
        return taskList.isEmpty();
    }

    /**
     * This method will format specific task, format it to output style and return it
     *
     * @param taskId the index of task the program wants to output
     * @return the formatted string about this task for output
     */
    public String getTaskOutputStyle(int taskId) {
        return taskList.get(taskId).toString();
    }

    /**
     * This method will format specific task, format it to saving file style and return it
     *
     * @param taskId the index of task the program wants to write to local files
     * @return the formatted string about this task for saving
     */
    public String getTaskSavingStyle(int taskId) {
        return taskList.get(taskId).toSavedFile();
    }

    /**
     * This method will add a task into the TaskList and return the newly added task
     *
     * @param newTask the new task to be added
     * @return the newly added task
     */
    public Task addTask(Task newTask) {
        taskList.add(newTask);
        return newTask;
    }

    /**
     * This method will delete the specific task from the TaskList and return the newly deleted task
     *
     * @param taskId the index of the task to be deleted
     * @return the newly deleted task
     */
    public Task delTask(int taskId) {
        Task temp = taskList.get(taskId);
        taskList.remove(taskId);
        return temp;
    }

    /**
     * This method will set finished/unfinished status of a specific task and return it
     *
     * @param taskId the index of the task we want to set the status
     * @param newStatus the new status to be set to
     * @return the task that just get updated 
     */
    public Task setTaskStatus(int taskId, boolean newStatus) {
        Task temp = taskList.get(taskId);
        temp.setStatus(newStatus);
        return temp;
    }

}
