package narcibot;

/**
 * Class for the abstraction of tasks.
 */
public class Task {
    private String name;
    private boolean isDone;

    /**
     * Constructor of task with name.
     *
     * @param name
     */
    public Task(String name) {
        this.name = name;
    }

    /**
     * Constructor of task with name and status.
     *
     * @param name
     * @param isDone status of the task
     */
    public Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    /**
     * Status of the task with format [status] name.
     *
     * @return String
     */
    public String getStatus() {
        String status = isDone ? "[X] " : "[ ] ";
        return status + name;
    }

    /**
     * Mark the status of the task as done.
     */
    public void markDone() {
        System.out.print("[X] " + name);
        isDone = true;
    }

    /**
     * Mark the status of the task as not done.
     */
    public void markNotDone() {
        System.out.print("[ ] " + name);
        isDone = false;
    }

    /**
     * Returns a string with the format required in the save file.
     *
     * @return String
     */
    public String save() {
        return (isDone ? "1" : "0" )+ "|" + name;
    }

    public boolean inName(String name) {
        return this.name.contains(name);
    }

    public void update(String newTime) {
    }
}
