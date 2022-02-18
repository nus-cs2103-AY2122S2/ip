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
     * @param task Specific task to be added.
     */
    public void addTask(Task task) {
        storage.getList().add(task);
    }

    /**
     * Deletes a task from the current storage.
     * @param index Index of a specific task to be deleted.
     */
    public void deleteTask(int index) {
        this.getTaskArray().remove(index);
    }

    /**
     * Returns an ArrayList which is used to store tasks which contains
     * the specific keyword.
     * @param keyword Keyword used to find matching tasks in the list.
     * @return all String in the Tasklist which contains the matching keywords.
     */
    public ArrayList<Task> findTask(String keyword) {
        ArrayList<Task> containsInput = new ArrayList<Task>();
        for (Task task: this.getTaskArray()) {
            if (task.toString().contains(keyword)) {
                containsInput.add(task);
            }
        }
        return containsInput;
    }

    /**
     * Shows a list of tasks from the current storage.
     * @return String which contains all the tasks in the current storage.
     */
    public String showTask() {
        return Ui.showListMessage(this);
    }

    /**
     * Returns an ArrayList which contains different tasks.
     * @return an arrayList which contains tasks.
     */
    public ArrayList<Task> getTaskArray() {
        return storage.getList();
    }
}
