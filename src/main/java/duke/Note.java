package duke;

public class Note {

    private final String label;
    private final String description;

    Note(String label, String description) {
        this.label = label;
        this.description = description;
    }

    public String getLabel() {
        return label;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return label + ": " + description;
    }

}
