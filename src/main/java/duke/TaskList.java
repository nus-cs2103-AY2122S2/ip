package duke;

import java.util.ArrayList;

/**
 * Represent list of tasks entered by user
 */
public class TaskList {
    /**
     * Arraylist of Tasks
     */
    private final ArrayList<Task> actions = new ArrayList<>();

    /**
     * Adds Task to the TaskList
     *
     * @param type Type of task to be added
     * @param inputs Array of parsed input from user
     * @return Boolean representing success of add operation
     * @throws EmptyDescriptionException if description is empty
     */
    public boolean add(TaskType type, String[] inputs) throws EmptyDescriptionException {
        if (inputs[0].trim().equals("")) {
            throw new EmptyDescriptionException("ToDo cannot be empty");
        }

        boolean isAddSuccess;
        switch (type) {
        case TODO :
            isAddSuccess = actions.add(new ToDo(inputs[0]));
            break;
        case DEADLINE:
            isAddSuccess = actions.add(new Deadline(inputs[0], inputs[1]));
            break;
        case EVENT:
            isAddSuccess = actions.add(new Event(inputs[0], inputs[1]));
            break;
        default:
            isAddSuccess = false;
            break;
        }

        return isAddSuccess;
    }

    /**
     * Adds Task to the list
     *
     * @param type TaskType of task to be added
     * @param inputs Parsed input array
     */
    public void load(TaskType type, String[] inputs) {
        Task task = null;
        switch (type) {
        case TODO :
            task = new ToDo(inputs[1]);
            break;
        case DEADLINE:
            task = new Deadline(inputs[1], inputs[2]);

            break;
        case EVENT:
            task = new Event(inputs[1], inputs[2]);
            break;
        default:
            break;
        }
        if (inputs[0].equals("1")) {
            task.markCompleted();
        } else {
            task.markIncompleted();
        }
        actions.add(task);
    }

    /**
     * Prints out Tasks in the TaskList
     */
    public void print() {
        for (int i = 0; i < actions.size(); i++) {
            System.out.print(i + 1);
            System.out.print(".");
            actions.get(i).print();
        }
    }

    /**
     * Prints out a specific task
     *
     * @param i Task index that is to be printed out
     */
    public void print(int i) {
        actions.get(i - 1).print();
    }

    /**
     * Marks a specific task as complete
     *
     * @param index Task index to be marked as complete
     */
    public void markComplete(int index) {
        this.actions.get(index - 1).markCompleted();
    }

    /**
     * Marks a specific task as incomplete
     *
     * @param index Task index to be marked as incomplete
     */
    public void markIncomplete(int index) {
        this.actions.get(index - 1).markIncompleted();
    }

    /**
     * Returns number of tasks stored
     *
     * @return int representing number of task stored
     */
    public int getLength() {
        return this.actions.size();
    }

    /**
     * Deletes specific task
     *
     * @param i index of task to be deleted
     */
    public void delete(int i) {
        actions.remove(i - 1);
    }

    /**
     * Returns Task at specific index
     *
     * @param i index of task
     * @return task at index i
     */
    public Task getTask(int i) {
        return actions.get(i - 1);
    }

    /**
     * Prints put tasks that are found to match input
     *
     * @param search input String to search for
     */
    public void findTask(String search) {
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < actions.size(); i++) {
            Task task = actions.get(i);
            if (task.getDetails()[2].contains(search)) {
                System.out.print(i + 1);
                System.out.print(".");
                task.print();
            }
        }
    }
}
