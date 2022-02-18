package duke;

/**
 * Represents a place
 */
public class Place {

    private final String name;
    private final String description;

    /**
     * Constructs an instance of a place.
     *
     * @param name a string representing the name of the place.
     * @param description a string representing the description or notes about the place.
     */
    public Place(String name, String description) {
        this.name = name;
        this.description = description;
    }

    /**
     * Fetches the name of the place.
     *
     * @return a string representing the name of the place.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Fetches the description of the place.
     *
     * @return a string representing the description of the place.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[" + this.name + "] " + this.description;
    }
}
