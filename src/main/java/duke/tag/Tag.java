package duke.tag;

/**
 * The type Tag.
 */
public class Tag {
    private final String tag;

    /**
     * Instantiates a new Tag.
     *
     * @param tag the tag
     */
    public Tag(String tag) {
        this.tag = tag;
    }

    /**
     * Gets tag.
     *
     * @return the tag
     */
    public String getTag() {
        return this.tag;
    }

    @Override
    public String toString() {
        return String.format("#{%s}", this.getTag());
    }
}
