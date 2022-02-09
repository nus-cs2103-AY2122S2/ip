package duke.task;

/**
 * Represents a Tag action.
 */
public class Tag {
    private String tag;

    /**
     * A Tag constructor to initialise a <code>Tag</code> object. A <code>Tag</code>
     * corresponds to an action represented by a String.
     * E.g., <code>tag 2 #fun</code>.
     *
     * @param tag
     */
    public Tag(String tag) {
        this.tag = tag;
    }

    /**
     * Returns the string representation of the <code>Tag</code> action.
     *
     * @return the string representation of the <code>Tag</code> action.
     */
    @Override
    public String toString() {
        return tag;
    }
}
