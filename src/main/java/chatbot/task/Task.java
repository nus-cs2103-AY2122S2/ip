package chatbot.task;

import chatbot.datetime.Timestamp;

/**
 * Represents the type Task.
 */
public class Task {

    private final String title;
    private final String type;
    private final Timestamp timestamp;
    private String done;

    /**
     * Instantiates a new Task.
     *
     * @param title    The title of the task.
     * @param type     The type of the task.
     * @param timestamp The timestamp of the task.
     */
    public Task(String title, String type, Timestamp timestamp) {
        this.title = title;
        this.type = type;
        this.timestamp = timestamp;
        this.done = " ";
    }

    /**
     * Instantiates a new Task.
     *
     * @param title    The title of the task.
     * @param type     The type of the task.
     * @param done     The completion status of the task.
     * @param timestamp The timestamp of the task.
     */
    public Task(String title, String type, String done, Timestamp timestamp) {
        this.title = title;
        this.type = type;
        this.done = done;
        this.timestamp = timestamp;
    }

    /**
     * Gets done.
     *
     * @return The completion status of this task.
     */
    public String getDone() {
        return done;
    }

    /**
     * Gets type.
     *
     * @return The type of this task.
     */
    public String getType() {
        return type;
    }

    /**
     * Gets title.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gets timestamp.
     *
     * @return The Timestamp of this task.
     */
    public Timestamp getTimestamp() {
        return timestamp;
    }

    /**
     * Marks this task as completed.
     */
    public void mark() {
        done = "X";
    }

    /**
     * Unmarks this task, indicating that it is incomplete.
     */
    public void unmark() {
        done = " ";
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s", type, done, title);
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Task) {
            Task t = (Task) other;
            return (
                t.getTitle().equals(title)
                        && t.getType().equals(type)
                        && t.getTimestamp().equals(timestamp)
                        && t.getDone().equals(done)
                );
        } else {
            return false;
        }
    }
}
