package funbox.task;

//@@author junrong98-reused
//Reused from https://nus-cs2103-ay2122s2.github.io/website/admin/ip-w2.html
// with minor modifications

import java.util.ArrayList;

/**
 * The task class represents the task user's wants to do
 */
public class Task {
    public String description;
    public boolean isDone;
    public String type;
    public ArrayList<String> tags;

    /**
     * Constructor for the Task.
     *
     * @param description The description of the task.
     * @param type The type of task (Deadline, Event, Todo).
     */
    public Task(String description, String type) {
        this.description = description;
        this.isDone = false;
        this.type = type;
        this.tags = new ArrayList<String>();
    }

    /**
     * The icon representing the current status of the task
     *
     * @return Return "X" if status of task is completed else return " "
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Set the task to done
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * Set the task to unfinished
     */
    public void setUndone() {
        this.isDone = false;
    }

    /**
     * Sets task to done
     */
    public void presetDone() {
        this.isDone = true;
    }

    /**
     * Shows the current tags of the task.
     *
     * @return Returns all tags of the task.
     */
    public String showTags() {
        String result = "";
        int lth = tags.size();
        for (int i = 0; i < lth; i++) {
            if (i == lth - 1) {
                result = result.concat(tags.get(i));
            } else {
                result = result.concat(tags.get(i)).concat(" ");
            }
        }
        return result == "" ? "No tags found!" : result;
    }

    /**
     * Add tag to task.
     *
     * @param tag The tags to be added to the task.
     */
    public void addTag(String tag) {
        tags.add("#".concat(tag));
    }

    /**
     * Return a string representation.
     *
     * @return Return a string.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}