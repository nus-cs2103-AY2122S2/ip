package duke.task;

/**
 * A class to represent a Task
 * created by user of the ChatBot.
 */
public abstract class Task {
    /** String description of current task */
    private final String name;

    /** Whether the task is marked as done */
    private boolean isDone;

    protected Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    /**
     * Decodes task data previously saved to file to
     * a task with its saved info.
     *
     * @param taskData String containing data of the task.
     * @return Task corresponding to saved data.
     * @throws IllegalArgumentException If task data to corrupt is corrupted.
     */
    public static Task decodeTaskData(String taskData) throws IllegalArgumentException {
        String[] data = taskData.split(",");
        assert data.length == 4 : "Data file should have 4 comma separated values";
        String type = data[0];
        String name = data[1];
        String isDone = data[2];
        String date = data[3];
        Task task = null;
        switch (type) {
        case "T":
            task = new ToDo(name);
            break;
        case "E":
            task = new Event(name, date);
            break;
        case "D":
            task = new Deadline(name, date);
            break;
        default:
            // Type should only be of 'T', 'E' or 'D'.
            throw new IllegalArgumentException("Corrupted data");
        }

        assert task != null : "Task should not be null";
        if (isDone.equals("Y")) {
            task.markDone();
        }
        return task;
    }

    /**
     * Get a checkbox describing whether the
     * task is done or not.
     *
     * @return String representing done status of task.
     */
    protected String getDoneStatusCheckbox() {
        return this.isDone ? "[X]" : "[ ]";
    }

    /**
     * Gets the string representation of current
     * task with its name and any additional info.
     *
     * @return String representing current task.
     */
    public abstract String getDescription();

    /**
     * Gets the serialised data of current task,
     * separated by commas, for storage in disk file.
     *
     * @return String representing data of current task.
     */
    public abstract String encodeTaskData();

    /**
     * Marks current task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Marks current task as undone.
     */
    public void unmarkDone() {
        this.isDone = false;
    }

    /**
     * Gets the name of the Task.
     *
     * @return Name of task.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets a boolean on whether the
     * task is done.
     *
     * @return True if task was marked done,
     * false otherwise.
     */
    public boolean isDone() {
        return this.isDone;
    }
}
