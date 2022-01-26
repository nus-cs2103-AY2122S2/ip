package com.duke.modules;

import com.duke.tasks.Task;

import java.io.IOException;
import java.util.ArrayList;

/**
 * A class responsible for dealing with operations on a task list.
 */
public class TaskList {
  private Storage storage;
  private ArrayList<Task> taskList;

  public TaskList() {};

  public TaskList(Storage storage) {
    this.taskList = new ArrayList<>();
    this.storage = storage;
    storage.loadList(taskList);
  }

  /**
   * Adds a task to the task list and saves the list to a text file.
   * @param task The Task to be added
   * @throws IOException On failure of read or write operations.
   */
  public void addTask(Task task) throws IOException {
    taskList.add(task);
    storage.saveList(taskList);
  }

  /**
   * Adds a task to the task list.
   * @param task The Task to be added.
   */
  public void addTaskWithoutSaving(Task task) {
    taskList.add(task);
  }

  public int getTaskListSize() {
    return taskList.size();
  }

  /**
   * Returns a Task object from the given specific index in the task list.
   * @param i Index of the task list.
   * @return A Task object.
   */
  public Task getTask(int i) {
    return taskList.get(i);
  }

  /**
   * Removes a task from a specified index in the task list and saves the list to a text file.
   * @param i Index of the task list.
   * @throws IOException On failure of read or write operations.
   */
  public void removeTask(int i) throws IOException {
    taskList.remove(i);
    storage.saveList(taskList);
  }

  public void removeTaskWithoutSaving(Task task) {
    taskList.add(task);
  }

  /**
   * Marks a task from a specified index in the task list as done and saves the list to a text file.
   * @param i Index of the task list.
   * @throws IOException On failure of read or write operations.
   */
  public void markTask(int i) throws IOException {
    taskList.get(i).setStatus(true);
    storage.saveList(taskList);
  }

  /**
   * Unmarks a task from a specified index in the task list as done and saves the list to a text file.
   * @param i Index of the task list.
   * @throws IOException On failure of read or write operations.
   */
  public void unmarkTask(int i) throws IOException {
    taskList.get(i).setStatus(false);
    storage.saveList(taskList);
  }

  /**
   * Clears all tasks in the task list as done and saves the list to a text file.
   * @throws IOException On failure of read or write operations.
   */
  public void clearList() throws IOException {
    taskList = new ArrayList<>();
    storage.saveList(taskList);
  }

  public void setListWithoutSaving(ArrayList<Task> taskList) {
    this.taskList = taskList;
  }

  /**
   * Returns a formatted string displaying the tasks in the task list.
   * Returns a predefined empty list message if the list is empty.
   * @return A string describing the contents of the task list.
   */
  public String displayList() {
    String output = "";
    if (taskList.size() == 0) {
      output = "LUMU: Your list is empty!";
    } else {
      for (int i = 0; i < taskList.size(); i++) {
        Task currTask = taskList.get(i);
        output = output + String.valueOf(i + 1) + ". " + currTask.toString() + "\n";
      }
    }
    return output.trim();
  }
}
