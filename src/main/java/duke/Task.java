package duke;

import java.util.ArrayList;

/**
 * Represent Task with description
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected ArrayList<String> tagList;

    /**
     * Creates a new Task with description
     *
     * @param description Description of Task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.tagList = new ArrayList<>();
    }

    /**
     * Returns Status Icon of current task as a String representation
     *
     * @return String representation of current task status icon
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    /**
     * Returns Description of task
     *
     * @return String Description of task
     */
    public String getDescription() {
        return description;
    }


    /**
     * Set current task status to done
     */
    public void setDone() {
        isDone = true;
    }

    /**
     * Set current task status to not done
     */
    public void setUndone() {
        isDone = false;
    }

    /**
     * Method to add a tag to task
     */
    public void addTag(String tag) {
        if(tag.startsWith("#")) {
            tagList.add(tag);
        } else {
            tagList.add("#" + tag);
        }
    }

    /**
     * Method to remove a tag from task
     *
     * @param tagIndex index of tag to be removed
     * @return String of removed tag
     */
    public String removeTag(int tagIndex) {
        return tagList.remove(tagIndex-1);
    }

    /**
     * Getter method to get tag ArrayList
     */
    public ArrayList<String> getTags() {
        return tagList;
    }


    /**
     * Returns String representation of {@code Task}.
     *
     * @return String representation of task of format {@code StatusIcon + Description}
     */
    @Override
    public String toString() {
        return getStatusIcon() + " " + this.description;
    }
}