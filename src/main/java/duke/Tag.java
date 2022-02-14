package duke;

/**
 * Represents tag for tasks.
 */
public class Tag {
    private String tagName;

    /**
     * Constructs tag object.
     * @param tagName
     */
    public Tag(String tagName) {
        this.tagName = tagName;
    }

    /**
     * Returns a String representation of a tag object.
     * @return String representation of the tag object.
     */
    @Override
    public String toString() {
        return "[" + tagName + "]";
    }
}
