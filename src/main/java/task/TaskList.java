package task;

import java.util.ArrayList;

/**
 * Represents a collection of Tasks using an ArrayList
 */
public class TaskList {
    private final ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public Integer size() {
        return taskList.size();
    }

    public Task get(Integer i) {
        return taskList.get(i);
    }

    public void add(Task task) {
        taskList.add(task);
    }

    /**
     * Removes a selected task from the TasksList
     * @param taskToDelete Task that is to be removed from the TasksList
     * @return true if successfully deleted, false otherwise
     */
    public boolean removeFromTaskList(Task taskToDelete) {
        return taskList.remove(taskToDelete);
    }

    /**
     * Iterates through every Task in the TasksList and prints all Tasks that contain the keyword
     * @param keyword string that is used to search for a match in the TasksList
     */
    public void find(String keyword) {
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).toString().contains(keyword)) {
                System.out.print(String.format("%d. %s \n", i+1, taskList.get(i).toString()));
            }
        }
    }

    /**
     * Prints every Task in the TasksList witch each task occupying a new line
     */
    public void printTaskList() {
        for (int i = 0; i < taskList.size(); i++) {
            System.out.print(String.format("%d. %s \n", i+1, taskList.get(i).toString()));
        }
    }
}
