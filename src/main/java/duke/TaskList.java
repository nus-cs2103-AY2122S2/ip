package duke;

import java.util.ArrayList;

/**
 * Represent list of tasks entered by user
 */
public class TaskList {
    /**
     * Arraylist of Tasks
     */
    private final ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Adds Task to the TaskList
     *
     * @param type Type of task to be added
     * @param inputs Array of parsed input from user
     * @return Boolean representing success of add operation
     * @throws EmptyDescriptionException if description is empty
     */
    public boolean addTask(TaskType type, String[] inputs) throws EmptyDescriptionException {
        if (inputs[0].trim().equals("")) {
            throw new EmptyDescriptionException("Description of task can't be empty");
        }

        boolean isAddSuccess;
        switch (type) {
        case TODO :
            isAddSuccess = tasks.add(new ToDo(inputs[0]));
            break;
        case DEADLINE:
            if (inputs[1].trim().equals("")) {
                throw new EmptyDescriptionException("Time cant be empty");
            }
            isAddSuccess = tasks.add(new Deadline(inputs[0], inputs[1]));
            break;
        case EVENT:
            if (inputs[1].trim().equals("")) {
                throw new EmptyDescriptionException("Time cant be empty");
            }
            isAddSuccess = tasks.add(new Event(inputs[0], inputs[1]));
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
    public void loadTasks(TaskType type, String[] inputs) {
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

        assert task != null : "Task cannot be null";

        if (inputs[0].equals("1")) {
            task.markCompleted();
        } else {
            task.markIncompleted();
        }

        tasks.add(task);
    }

    @Override
    public String toString() {
        String description = "";

        if (tasks.isEmpty()) {
            return "You have no tasks";
        }

        for (int i = 0; i < tasks.size(); i++) {

            description = description + (i + 1);
            description = description + ".";
            description = description + tasks.get(i).toString();
        }
        return description;
    }

    public String toString(int index) {
        return tasks.get(index - 1).toString();
    }

    /**
     * Marks a specific task as complete
     *
     * @param index Task index to be marked as complete
     */
    public void markComplete(int index) {
        this.tasks.get(index - 1).markCompleted();
    }

    /**
     * Marks a specific task as incomplete
     *
     * @param index Task index to be marked as incomplete
     */
    public void markIncomplete(int index) {
        this.tasks.get(index - 1).markIncompleted();
    }

    /**
     * Returns number of tasks stored
     *
     * @return int representing number of task stored
     */
    public int getLength() {
        return this.tasks.size();
    }

    /**
     * Deletes specific task
     *
     * @param i index of task to be deleted
     */
    public void deleteTask(int i) {
        tasks.remove(i - 1);
    }

    /**
     * Returns Task at specific index
     *
     * @param i index of task
     * @return task at index i
     */
    public Task getTask(int i) {
        return tasks.get(i - 1);
    }

    /**
     * Prints put tasks that are found to match input
     *
     * @param search input String to search for
     */
    public String findTask(String search) {
        String output = "Here are the matching tasks in your list:\n";
        int numOfTasks = 0;
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.getDetails()[2].contains(search)) {
                output = output + (i + 1);
                output = output + ".";
                output = output + task;
                numOfTasks++;
            }
        }
        if (numOfTasks == 0) {
            return "Sorry, there were no tasks that matched your search";
        }
        return output;
    }
}
