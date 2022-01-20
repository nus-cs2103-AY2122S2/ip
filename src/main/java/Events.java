public class Events extends Task {
    protected static String type = "[E]";

    public Events(String description, boolean isDone) {
        super(description, isDone);
        String[] temp = description.split("/at ");
        if (temp.length > 1) {
            this.description = temp[0] + " (at: " + temp[1] + ")";
        } else {
            this.description = description;
        }
    }

    public Events(String description) {
        this(description, false);
    }

    @Override
    public String toString() {
        return this.isDone ? "[E][X] " + this.description
                : "[E][ ] " + this.description;
    }

}
