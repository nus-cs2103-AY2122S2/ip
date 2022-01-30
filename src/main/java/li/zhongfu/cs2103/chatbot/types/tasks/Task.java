package li.zhongfu.cs2103.chatbot.types.tasks;

import java.io.Serializable;

import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Object that represents a to-do list task. Contains a name and a state.
 * 
 * Currently, it probably isn't possible for the state to be inconsistent e.g. after deserializing
 * a (potentially malicious) serialized object, but keep this in mind if implementing subclasses
 * with more advanced logic.
 */
public abstract class Task implements Serializable {
    private String name;
    private boolean done = false;
    protected static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
    /**
     * Creates a new task with the given name.
     * 
     * @param name name of task
     */
    protected Task(String name) {
        this.name = name; 
    }
    
    /**
     * Sets task as done or not done.
     * 
     * @param done true if task is to be set as done, false otherwise
     */
    public void setDone(boolean done) {
        this.done = done;
    }
    
    /**
     * Returns name of task.
     * 
     * @return name of task
     */
    public String getName() {
        return this.name;
    }
    
    /**
     * Returns "done" state of task.
     * 
     * @return true if task is marked as done, false otherwise
     */
    public boolean getDone() {
        return this.done;
    }

    /**
     * Returns task information in a human-readable string.
     * 
     * @return task details in a human-readable string
     */
    abstract public String toString();

    @Override
    public boolean equals(Object o) {
        if (o != null && this.getClass() == o.getClass()) {
            Task task = (Task) o;
            return this.name == task.name && this.done == task.done;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name, this.done);
    }
}
