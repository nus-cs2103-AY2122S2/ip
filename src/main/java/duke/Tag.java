package duke;

public class Tag {
    private String message;

    public Tag(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "#" + message;
    }
}
