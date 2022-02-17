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
     * Iterates through every Task in the TasksList and returns a String with all the Tasks that contain the keyword
     * @param keyword string that is used to search for a match in the TasksList
     */
    public String find(String keyword) {
        String foundTasks = "";
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).toString().contains(keyword)) {
                foundTasks += String.format("%d. %s \n", i + 1, taskList.get(i).toString());
            }
        }
        return foundTasks;
    }

    @Override
    public String toString() {
        String taskListToString = "";
        for (int i = 0; i < taskList.size(); i++) {
            taskListToString += String.format("%d. %s \n", i + 1, taskList.get(i).toString());
        }
        return taskListToString;
    }
}
