package duke.task;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Represents a task made by the user. A <code>Task</code> object cannot be
 * created as it is an abstract base class for all tasks. The task can
 * be mark as done after the user complete the specific task.
 */
public abstract class Task {
    protected String description;
    protected boolean isMark;

    /**
     * Creates an instance of a Task object.
     *
     * @param description the description of the task.
     */
    public Task(String description) {
        this.description = description;
    }

    /**
     * Creates an instance of a Task object.
     *
     * @param description the description of the task.
     * @param isMark whether the task is mark.
     */
    public Task(String description, boolean isMark) {
        this.description = description;
        this.isMark = isMark;
    }

    /**
     * Marks the task object as done.
     */
    public void markTask() {
        isMark = true;
    }

    /**
     * Unmarks the task object as not done.
     */
    public void unmarkTask() {
        isMark = false;
    }

    /**
     * Returns the visual description of the task.
     *
     * @return description of the task.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", isMark ? "X" : "  ", description);
    }

    /**
     * Returns the data of the task.
     *
     * @return data of the task.
     */
    public String toData() {
        return String.format("%d|%s", isMark ? 1 : 0, description);
    }

    /**
     * Converts the data in string representation and
     * creates a new Task object.
     *
     * @param data the data information about the task.
     * @return a new task object.
     * @throws IOException if read tasklist data fails.
     */
    public static Task createTask(String data) throws IOException {
        try {
            String[] dataArgs = data.split("\\|");
            int taskDone = Integer.parseInt(dataArgs[1]);
            boolean isMarkValid = taskDone == 1 || taskDone == 0;
            boolean isTaskMark = taskDone == 1;

            if (!isMarkValid) {
                throw new IOException();
            }

            switch (dataArgs[0]) {
            case "T":
                return new Todo(dataArgs[2], isTaskMark);
            case "D":
                return new Deadline(dataArgs[2], isTaskMark, LocalDate.parse(dataArgs[3]),
                        LocalTime.parse(dataArgs[4]));
            case "E":
                return new Event(dataArgs[2], isTaskMark, LocalDate.parse(dataArgs[3]),
                        LocalTime.parse(dataArgs[4]), LocalTime.parse(dataArgs[5]));
            default:
                throw new IOException();
            }
        } catch (NumberFormatException | IOException e) {
            throw new IOException();
        }
    }
}
