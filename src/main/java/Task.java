public class Task {
    // class constants
    private static String COMPLETION_MARK = "X";
    private static String INCOMPLETE_MARK = " ";


    // instance attributes
    private String description;
    private boolean completed;


    Task (String description) {
        this.description = description;
        this.completed = false;
    }

    private String check_if_complete() {
        if (this.completed) {
            return Task.COMPLETION_MARK;
        } else {
            return Task.INCOMPLETE_MARK;
        }
    }

    public void mark_complete() {
        this.completed = true;
    }

    public void mark_incomplete() {
        this.completed = false;
    }

    @Override
    public String toString() {
        return "[" + this.check_if_complete() + "] " + this.description;
    }
}
