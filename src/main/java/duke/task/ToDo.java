package duke.task;

import duke.tag.Tag;

/**
 * The type To-do.
 */
public class ToDo extends Task {
    /**
     * Instantiates a new To do with tag
     *
     * @param description the description
     * @param tag         the tag
     */
    public ToDo(String description, Tag tag) {
        super(description, tag);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
