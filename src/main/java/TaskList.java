package duke;

import java.util.ArrayList;

/**
 * Contains the task list e.g., it has operations to add/delete tasks in the list
 *
 */
public class TaskList {
    private ArrayList<Task> taskList;

    /**
     * Initializes taskList with empty list.
     */
    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    /**
     * If an existing list is present, pass the data
     * through the parser to retrieve data and therefore the tasks.
     * @param taskList string of data from text file.
     */
    public TaskList(ArrayList<String> taskList) {
        for (int i = 0; i < taskList.size(); i++) {
            this.taskList.add(new Task(taskList.get(i)));
        }
    }


    /**
     * Get task at index
     * @param index
     * @return Task at index
     */
    public Task get(int index) {
        return this.taskList.get(index);
    }

    /**
     * Get size of the taskList.
     * @return (int) number of tasks.
     */
    public int size() {
        return this.taskList.size();
    }

    /**
     * Add task to the taskList.
     * @param task to be added to the List.
     */
    public void add(Task task) {
        this.taskList.add(task);
    }

    /**
     * Add string into a task which is then added to the List.
     * @param description
     */
    public void add(String description) {
        this.taskList.add(new Task(description));
    }

    /**
     * Removes the task at specified index
     * @param index
     */
    public void remove(int index) {
        this.taskList.remove(index);

    }

    /**
     * Marks the task at the specific index as done.
     * @param index
     */
    public void markAsDone(int index) {
        this.taskList.get(index).markAsDone();
    }

    /**
     * Marks the task as the specific index as undone.
     * @param index
     */
    public void markAsUndone(int index) {
        this.taskList.get(index).unmarkAsDone();
    }
}

