package spark.tasks.tasktypes;

public class ToDo extends Task {
    /**
     * Creates a new ToDo.
     *
     */
    public ToDo (String name) {
        super(name);
    }

    /**
     * Generates a new ToDo and initialises it with the
     * given completion status.
     *
     */
    public ToDo(boolean isDone, String title) {
        super(isDone, title);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    @Override
    public String encodeTask() {
        return String.format("T @@@ %b @@@ %s", super.isDone(), super.getTitle());
    }
}
