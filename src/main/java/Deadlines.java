public class Deadlines extends Task {
    protected static String type = "[D]";

    public Deadlines(String description, boolean isDone) {
        super(description, isDone);
        String[] temp = description.split("/by ");
        if (temp.length > 1) {
            this.description = temp[0] + " (by: " + temp[1] + ")";
        } else {
            this.description = description;
        }
    }

    public Deadlines(String description) {
        this(description, false);
    }

    @Override
    public String toString() {
        return this.isDone ? "[D][X] " + this.description
                : "[D][ ] " + this.description;
    }
}
