package duke;

import java.util.ArrayList;

/**
 * Represent list of tasks entered by user
 */
public class TaskList {

    static final int TASK_INDEX = 0;
    static final int COMPLETED_INDEX = 1;
    static final int DESCRIPTION_INDEX = 2;
    static final int TIME_INDEX = 3;

    static final int USER_DESCRIPTION_INDEX = 0;
    static final int USER_TIME_INDEX = 1;

    static final String COMPLETED_STRING = "1";

    static final int INDEX_CORRECTION = 1;

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
        if (inputs[USER_DESCRIPTION_INDEX].trim().equals("")) {
            throw new EmptyDescriptionException("Description of task can't be empty");
        }

        boolean isAddSuccess;
        switch (type) {
        case TODO :
            isAddSuccess = tasks.add(new ToDo(inputs[0]));
            break;
        case DEADLINE:
            if (inputs[USER_TIME_INDEX].trim().equals("")) {
                throw new EmptyDescriptionException("Time cant be empty");
            }
            isAddSuccess = tasks.add(new Deadline(inputs[0], inputs[1]));
            break;
        case EVENT:
            if (inputs[USER_TIME_INDEX].trim().equals("")) {
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
     * @param input Comma separated input values
     */
    public void loadTask(String input) {
        String[] inputs = input.split(",");
        TaskType type = Parser.parseTaskType(inputs[TASK_INDEX]);

        assert type != null : "Task type cannot be null";

        Task task;
        switch (type) {
        case TODO :
            task = new ToDo(inputs[DESCRIPTION_INDEX]);
            break;
        case DEADLINE:
            task = new Deadline(inputs[DESCRIPTION_INDEX], inputs[TIME_INDEX]);
            break;
        case EVENT:
            task = new Event(inputs[DESCRIPTION_INDEX], inputs[TIME_INDEX]);
            break;
        default:
            task = null;
            break;
        }

        assert task != null : "Task cannot be null";

        if (inputs[COMPLETED_INDEX].equals(COMPLETED_STRING)) {
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

            description = description + (i + INDEX_CORRECTION);
            description = description + ".";
            description = description + tasks.get(i).toString();
        }
        return description;
    }

    public String toString(int index) {
        return tasks.get(index - INDEX_CORRECTION).toString();
    }

    /**
     * Marks a specific task as complete
     *
     * @param index Task index to be marked as complete
     */
    public void markComplete(int index) {
        this.tasks.get(index - INDEX_CORRECTION).markCompleted();
    }

    /**
     * Marks a specific task as incomplete
     *
     * @param index Task index to be marked as incomplete
     */
    public void markIncomplete(int index) {
        this.tasks.get(index - INDEX_CORRECTION).markIncompleted();
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
        tasks.remove(i - INDEX_CORRECTION);
    }

    /**
     * Returns Task at specific index
     *
     * @param i index of task
     * @return task at index i
     */
    public Task getTask(int i) {
        return tasks.get(i - INDEX_CORRECTION);
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
            System.out.println(task.getDetails()[DESCRIPTION_INDEX]);
            if (task.getDetails()[DESCRIPTION_INDEX].contains(search)) {
                output = output + (i + INDEX_CORRECTION);
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
