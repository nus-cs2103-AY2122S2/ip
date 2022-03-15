package duke.tasks;

/**
 * Represents a Task. A <code>Task</code> object corresponds to
 * a Task which may or may not contain an extension.
 */


public class Task {
    private boolean completed;
    private String name;
    private String type;
    private String extension = "";

    /**
     * Constructor for a Task which does not have an extension.
     * @param name
     * @param type
     */
    public Task(String name, String type) {
        this.name = name;
        this.completed = false;
        this.type = type;
    }
    /**
     * Constructor for a Task which has an extension.
     * @param name
     * @param type
     */
    public Task(String name, String type, String extension) {
        this.name = name;
        this.completed = false;
        this.type = type;
        this.extension = extension;
    }

    public String getName() {
        return this.name;
    }

    /**
     * Set a task as done.
     */
    public void setDone() {
        this.completed = true;
    }

    /**
     * Set a task as not done.
     */
    public void setUndone() {
        this.completed = false;
    }

    public void setName(String name) {
        this.name = name;
    }


    /**
     * Converts a string to compact format to be saved in the file
     * @return The compacted string
     */
    public String saveString() {
        int status = completed == true ? 1 : 0;
        if (extension.isEmpty()) {
            return this.type + "|" + status + "|" + name;
        } else {
            return this.type + "|" + status + "|" + name + "|" + extension;
        }
    }


    /**
     * @return the name of the task, along with an [X] if completed, [] otherwise
     */
    @Override
    public String toString() {
        if (completed) {
            return "[X] " + this.name;
        } else {
            return "[ ] " + this.name;
        }
    }

}
