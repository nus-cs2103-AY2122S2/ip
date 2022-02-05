package duke.task;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a task. Meant to be inherited by more specified tasks.
 */

public class Task {
    private String name;
    /**
     * To indicate whether the task has been completed.
     */
    private boolean isMarked;
    /**
     * Tags for the task.
     */
    private List<String> tags;

    /**
     * Initializes a new task.
     *
     * @param name Description or name of the task.
     */
    public Task(String name) {
        this.name = name;
        this.isMarked = false;
        this.tags = new ArrayList<String>();
    }

    public boolean isMarked() {
        return this.isMarked;
    }

    public String getName() {
        return this.name;
    }

    public List<String> getTags() {
        return this.tags;
    }

    /**
     * Marks the task as done.
     */
    public void setMarked(boolean isMarked) {
        this.isMarked = isMarked;
    }

    /**
     * Adds a new tag.
     */
    public void addTag(String tag) {
        tags.add(tag);
    }

    /**
     * Removes a tag.
     *
     * @param tag Tag to be removed.
     * @return Returns true if successfully removed. Remove false if does not exist.
     */
    public boolean removeTag(String tag) {
        if (!tags.contains(tag)) {
            return false;
        }

        tags.remove(tag);
        return true;
    }

    /**
     * Returns a string of all tags.
     *
     * @return All tags.
     */
    public String returnTags() {
        if (tags.size() == 0) {
            return "No tags";
        }

        String output = "";
        for (int i = 0; i < tags.size(); i++) {
            output += "#" + tags.get(i);
            if (i != tags.size() - 1) {
                output += " ";
            }
        }
        return output;
    }

    /**
     * Prints out a formatted version of the task with its done state.
     *
     * @return Formatted task.
     */
    public String toString() {
        String doneState = isMarked ? "X" : " ";
        return String.format("[%s] %s", doneState, name);
    }
}
