package tasks;

import exceptions.DukeException;

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

    public void setDone(boolean newDone) {
        this.done = newDone;
    }

    public boolean isDone() {
        return this.done;
    }

    public abstract String getDescription();
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