package mcbot;

import java.util.ArrayList;

import mcbot.task.Task;

/**
 * TaskList class to store the list of tasks. 
 */
public class TaskList {
    private ArrayList<Task> taskList;

    /**
     * Constructor for Tasklist. 
     */
    public TaskList() {
    }

    /**
     * Constructor for TaskList if a list of tasks is available. 
     * 
     * @param arrayList is the list of tasks. 
     */
    public TaskList(ArrayList<Task> arrayList) {
        taskList = arrayList;
    }

    /**
     * Method to get the task list. 
     * 
     * @return the list of tasks. 
     */
    public ArrayList<Task> getList() {
        return taskList;
    }

    /**
     * Method to get the size of the task list. 
     * 
     * @return the size of the task list.
     */
    public int size() {
        return taskList.size();
    }

    /**
     * Method to get a specific task at index i. 
     * 
     * @param i is the index of the task to retrieve. 
     * @return the task found at index i. 
     */
    public Task get(int i) {
        return taskList.get(i);
    }

    /**
     * Method to add task to the task list. 
     * 
     * @param t is the task to be added. 
     */
    public void add(Task t) {
        taskList.add(t);
    }

    /**
     * Method to remove task at index i. 
     * 
     * @param i is the index of the task to be removed. 
     */
    public void remove(int i) {
        taskList.remove(i);
    }

    /**
     * Method to find a task that matches with the string. 
     * 
     * @param taskName is the string to be matched with. 
     * @param ui is the user interface of McBot. 
     */
    public void find(String taskName, Ui ui) {
        boolean anyMatch = false;
        boolean isHeaderPrinted = false;
        for (Task t : taskList) {
            if (t.getTaskName().contains(taskName)) {
                anyMatch = true;
                if (!isHeaderPrinted && anyMatch) {
                    ui.printFind();
                    isHeaderPrinted = true;
                }
                ui.printTask(t);
            }
        }
        if (!anyMatch) {
            ui.noMatch();
        }
    }

    public String find(String taskName, Gui gui) {
        boolean anyMatch = false;
        boolean isHeaderPrinted = false;
        StringBuilder sb = new StringBuilder();
        for (Task t : taskList) {
            if (t.getTaskName().contains(taskName)) {
                anyMatch = true;
                if (!isHeaderPrinted && anyMatch) {
                    sb.append(gui.printFind() + "\n");
                    isHeaderPrinted = true;
                }
                sb.append(gui.printTask(t) + "\n");
            }
        }
        if (!anyMatch) {
            return gui.noMatch();
        }
        return sb.toString();
    }
}
