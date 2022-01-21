public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Provide status info of a task.
     *
     * @return "X" if the task is done; " " if it is not.
     */
    private String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Mark a task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Unmark a task.
     */
	public void unmarkAsDone() {
		this.isDone = false;
	}

    /**
     * Provide the status info and description of a task.
     *
     * @return A string includes status and description.
     */
    public String print() {
        return ("[" + this.getStatusIcon() + "] " + this.description);
    }
}
