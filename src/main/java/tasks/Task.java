package tasks;

import exceptions.InvalidTaskException;

import java.util.Objects;

public abstract class Task {
    private static final String TODO = "todo";
    private static final String DEADLINE = "deadline";
    private static final String EVENT = "event";

    private final String description;
    private Boolean isDone;

    /**
     * Constructor for the task object.
     *
     * @param description  description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public static Task of(String taskString) throws InvalidTaskException {
        Task task;
        String[] taskArr = taskString.split(" ", 2);
        String taskType = taskArr[0];
        String[] params;

        switch (taskType) {
            case TODO:
                if (taskArr.length <= 1) {
                    throw new InvalidTaskException("☹ OOPS!!! The description of a todo cannot be empty.");
                }
                task = new Todo(taskArr[1]);
                break;

            case DEADLINE:
                if (taskArr.length <= 1) {
                    throw new InvalidTaskException("☹ OOPS!!! The description of a task cannot be empty.");
                }
                params = taskArr[1].split(" /by ");
                if (params.length <= 1) {
                    throw new InvalidTaskException("☹ OOPS!!! The deadline of a task cannot be empty.");
                }
                task = new Deadline(params[0], params[1]);
                break;

            case EVENT:
                if (taskArr.length <= 1) {
                    throw new InvalidTaskException("☹ OOPS!!! The description of a event cannot be empty.");
                }
                params = taskArr[1].split(" /at ");
                if (params.length <= 1) {
                    throw new InvalidTaskException("☹ OOPS!!! The time of an event cannot be empty.");
                }
                task = new Event(params[0], params[1]);
                break;

            default:
                throw new InvalidTaskException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        return task;
    }

    public static Task fromCsv(String taskString, String delimiter) throws InvalidTaskException {
        Task task;
        String[] taskArr = taskString.split(delimiter);

        if (taskArr.length < 3) throw new InvalidTaskException("Insufficient values");

        String taskCode = taskArr[0];
        boolean taskCompleted = Objects.equals(taskArr[1], "x");
        String taskDescription = taskArr[2];

        switch (taskCode) {
            case Todo.TASK_CODE:
                task = new Todo(taskDescription);
                break;
            case Deadline.TASK_CODE:
                if (taskArr.length < 4) throw new InvalidTaskException("Insufficient values");
                String by = taskArr[3];
                task = new Deadline(taskDescription, by);
                break;
            case Event.TASK_CODE:
                if (taskArr.length < 4) throw new InvalidTaskException("Insufficient values");
                String at = taskArr[3];
                task = new Event(taskDescription, at);
                break;
            default:
                throw new InvalidTaskException("Task format is invalid");
        }

        if (taskCompleted) task.markAsCompleted();
        return task;
    }

    /**
     * Get the description of the task.
     *
     * @return description of the task
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Mark the current task as completed.
     */
    public void markAsCompleted() {
        this.isDone = true;
    }

    /**
     * Mark the current task as uncompleted.
     */
    public void markAsUncompleted() {
        this.isDone = false;
    }

    /**
     * Get the string representation of the task status.
     *
     * @return 'x' or ' ' depending on the status
     */
    public String getStatusIcon() {
        return isDone ? "x" : " ";
    }

    /**
     * Get the csv representation of the task.
     *
     * @return string in csv format
     */
    public abstract String toCsv(String delimiter);

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), this.description);
    }
}
