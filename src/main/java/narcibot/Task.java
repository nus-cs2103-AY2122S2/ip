package narcibot;

/**
 * Class for the abstraction of tasks.
 */
public class Task {
    private String name;
    private boolean done;

    /**
     * Constructor of task with name.
     * @param name
     */
    public Task(String name) {
        this.name = name;
    }

    /**
     * Constructor of task with name and status.
     * @param name
     * @param done status of the task
     */
    public Task(String name, boolean done) {
        this.name = name;
        this.done = done;
    }

    /**
     * Status of the task with format [status] name.
     * @return String
     */
    public String getStatus() {
        String status = done ? "[X] " : "[ ] ";
        return status + name;
    }

    /**
     * Mark the status of the task as done.
     */
    public void markDone() {
        System.out.print("[X] " + name);
        done = true;
    }

    /**
     * Mark the status of the task as not done.
     */
    public void markNotDone() {
        System.out.print("[ ] " + name);
        done = false;
    }

    /**
     * Returns a string with the format required in the save file.
     * @return String
     */
    public String save() {
        return (done ? "1" : "0" )+ "|" + name;
    }
}
