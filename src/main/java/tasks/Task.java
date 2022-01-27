package tasks;

import exceptions.DukeException;

public abstract class Task {

    public static String UNKNOWN_INPUT_ERROR_STRING = "I don't think I know what this is!";
    public static String BAD_DESCRIPTION_ERROR_STRING = "Description cannot be empty!";
    public static String INVALID_TASK_NUM_ERROR_STRING = "Not a valid task number!";
    public static String TASK_NUM_DOES_NOT_EXIST_ERROR_STRING = "Task %d does not exist!";

    public enum TaskType {
        TODO,
        EVENT,
        DEADLINE
    }
    public boolean isDone = false;

    public void setDone(boolean newDone) {
        this.isDone = newDone;
    }

    public boolean isDone() {
        return this.isDone;
    }

    public abstract String exportToString();

    public static Task importFromString(String exportedTask)
            throws IllegalArgumentException, ArrayIndexOutOfBoundsException, DukeException {
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