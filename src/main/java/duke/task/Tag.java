package duke.task;

/**
 * Tag object provides the function of attaching a tag to a Task object.
 * A user can create its own tag by using the command "tag indexNumber tagName".
 * Tag can only be one word!
 */
public class Tag {
    private String name;

    /**
     * Instantiates a Tag object with given name and target task.
     *
     * @param name Content of the tag.
     */
    public Tag(String name) {
        this.name = name;
    }

    /**
     * Checks if two tags are equal based on its name.
     *
     * @param otherTag Tag Tag object to be compared to.
     * @return boolean Boolean on whether otherTag is equal to this Tag.
     */
    @Override
    public boolean equals(Object otherTag) {
        assert otherTag instanceof Tag;
        Tag tagForComparison = (Tag) otherTag;
        return name.equals(tagForComparison.name);
    }

    /**
     * Creates and returns a String version of this Tag.
     *
     * @return String Returns the String version of this Tag.
     */
    @Override
    public String toString() {
        return "#" + name;
    }
}
