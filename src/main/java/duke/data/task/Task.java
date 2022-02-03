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
}
