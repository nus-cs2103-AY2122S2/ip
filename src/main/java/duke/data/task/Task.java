package duke.data.task;

import java.util.Arrays;
import java.util.List;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Provides status info of a task.
     *
     * @return "X" if the task is done; " " if it is not.
     */
    private String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks a task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Unmarks a task.
     */
    public void unmarkAsDone() {
        this.isDone = false;
    }

    /**
     * Provides the status info and description of a task.
     *
     * @return A string includes status and description.
     */
    public String toString() {
        return ("[" + this.getStatusIcon() + "] " + this.description);
    }

    /**
     * Provides the string representation of the task for data storage
     */
    public String toStoreInfo() {
        String status = isDone ? "1" : "0";
        return " | " + status + " | " + this.description + "\n";
    }

    public List<String> getWordsInDescription() {
        return Arrays.asList(this.description.split("\\s+"));
    }

    /**
     * Compares two objects and checks whether they are the same.
     *
     * @param obj The other object to be compared with.
     * @return True if the two tasks have the same description and status;
     *         False otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Task)) {
            return false;
        }

        Task t = (Task) obj;
        return t.description.equals(this.description) && t.isDone == this.isDone;
    }
}
