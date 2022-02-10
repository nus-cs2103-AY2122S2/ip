package jarvis.tags;

public class Tag {
    private final String name;

    /**
     * Constructor for the tag object.
     * @param name name of the tag
     */
    public Tag(String name) {
        this.name = name;
    }

    /**
     * Gets the name of the tag.
     * @return name of the tag
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the string representation of the task.
     * @return string of the task
     */
    @Override
    public String toString() {
        return String.format("#%s", this.name);
    }
}
