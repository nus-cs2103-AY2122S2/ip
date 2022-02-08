package duke;

import java.util.ArrayList;

/**
 * Represents a class which is responsible to handle
 * operations such as adding or deleting tasks.
 */
class TaskList {
    private Storage storage;
    private Ui Ui;

    TaskList(Storage storage) {
        this.storage = storage;
        this.Ui = new Ui();
    }

    /**
     * Adds a task into the current storage.
     * 
     * @param task Specific task to be added.
     */
    public void addTask(Task task) {
        storage.getList().add(task);
    }

    /**
     * Deletes a task from the current storage.
     * 
     * @param index Index of a specifc task to be deleted.
     */
    public void deleteTask(int index) { //might need to change
        this.getTaskArray().remove(index);
    }

    /**
     * Shows a list of tasks from the current storage.
     */
    public void showTask() {
        Ui.showListMessage(this);
    }

    /**
     * Returns an ArrayList which contains different tasks.
     */
    public ArrayList<Task> getTaskArray() {
        return storage.getList();
    }
    
}
