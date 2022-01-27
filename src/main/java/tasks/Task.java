package tasks;

import exceptions.DukeException;

/** A class that functions as an abstraction of a task. */
public abstract class Task {

    public static String unknownInputErrorString = "I don't think I know what this is!";
    public static String badDescriptionErrorString = "Description cannot be empty!";
    public static String invalidTaskNumErrorString = "Not a valid task number!";
    public static String taskNumDoesNotExistString = "Task %d does not exist!";

    public enum TaskType {
        TODO,
        EVENT,
        DEADLINE
    }

    public boolean done = false;

    /**
     * Sets the Task as done or undone.
     *
     * @param newDone True if the task is to be set as done, false to be set as undone.
     */
    public void setDone(boolean newDone) {
        this.done = newDone;
    }

    /**
     * Checks if the task is done or undone.
     *
     * @return True if the task is done, false otherwise.
     */
    public boolean isDone() {
        return this.done;
    }

    /** Method to be implemented by any Task: to be able to be represented by a string. */
    public abstract String exportToString();

    /**
     * Returns a task when given the string representation of it.
     *
     * @param exportedTask The string representation of a Task.
     * @return A Task object.
     * @throws DukeException If given task command was not formatted properly.
     */
    public static Task importFromString(String exportedTask)
        throws DukeException {
        String[] details = exportedTask.split(" ");
        Task task = null;
        switch (TaskType.valueOf(details[0])) {
            case TODO:
                task = new Todo(details[1]);
                task.setDone(Boolean.parseBoolean(details[2]));
                break;
            case EVENT:
                task = new Event(details[1], details[3]);
                task.setDone(Boolean.parseBoolean(details[2]));
                break;
            case DEADLINE:
                task = new Deadline(details[1], details[3]);
                task.setDone(Boolean.parseBoolean(details[2]));
                break;
        }
        return task;
    }
}