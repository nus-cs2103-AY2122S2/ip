package paggro.task;

import java.util.ArrayList;

/**
 * This class encapsulates a Tag object which contains a list of all the tasks on a Tag.
 */
public class Tag {
    /** The description of the Tag object. */
    private String description;
    /** The list of tasks with the Tag object. */
    private ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Constructor of Tag.
     *
     * @param description The description of this Tag object.
     */
    public Tag(String description) {
        this.description = description;
        tasks = new ArrayList<>();
    }

    /**
     * Returns the tasks list of the Tag object.
     *
     * @return ArrayList of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Adds the given task to this Tag object's list of tasks.
     *
     * @param task Task to be added to the list.
     */
    public void addTaskToTag(Task task) {
        tasks.add(task);
    }

    @Override
    public String toString() {
        return "#" + description;
    }
}
