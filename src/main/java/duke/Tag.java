package duke;

/**
 * This class specifies all the methods and variables of a tag.
 * @author Sim Jun Heng
 * @version CS2103T AY21/22 Sem 2
 */
public class Tag {
    // Class variables
    private String name;

    /**
     * Assigns tag name to this instance
     *
     * @param name the tag description
     */
    public Tag(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "#" + name;
    }

    public String getName() {
        return this.name;
    }
}
