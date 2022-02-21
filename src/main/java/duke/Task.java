package duke;

import java.util.ArrayList;

/**
 * Provides abstraction of an task in the todoList
 */
public abstract class Task {
    protected boolean finished;
    protected String content;
    protected ArrayList<Tag> tags;

    /**
     * Contructs an item object
     *
     * @param content description of what to do in String
     */
    public Task(String content) {
        this.content = content;
        this.finished = false;
        this.tags = new ArrayList<>();
    }

    /**
     * marks an item as done
     */
    public void finished() {
        this.finished = true;
    }

    /**
     * marks an item as unfinished
     */
    public void notFinished() {
        this.finished = false;
    }

    /**
     * returns a string representation of the task to be read by users.
     *
     * @return a string representation of the task
     */
    @Override
    public String toString() {

        String tagsToString = tagsToString(tags);
        if (finished) {
            return "[X] " + content + tagsToString;
        } else {
            return "[ ] " + content + tagsToString;
        }
    }

    /**
     * returns a string representation of the task to be used in writing to a local file.
     *
     * @return a string representation of the task
     */
    public String toData() {
        if (finished) {
            return "1:" + content;
        } else {
            return "0:" + content;
        }
    }

    public void addTag(String tagMessage) {
        tags.add(new Tag(tagMessage));
    }

    public void deleteTag(int tagNumber) {
        int index = tagNumber - 1;
        tags.remove(index);
    }

    private static String tagsToString(ArrayList<Tag> tags) {
        String tagsToString = "";
        for (int i = 0; i < tags.size(); i++) {
            tagsToString = tagsToString + " " + tags.get(i);
        }
        return tagsToString;
    }

}
