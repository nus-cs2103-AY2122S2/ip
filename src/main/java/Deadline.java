public class Deadline extends Task {
    private String deadlineDate;

    Deadline(String description, String deadlineDate) {
        super(description);
        this.deadlineDate = deadlineDate;
    }

    Deadline(String description, String deadlineDate, Boolean completed) {
        super(description, completed);
        this.deadlineDate = deadlineDate;
    }

    @Override
    public String getType() {
        return "Deadline";
    }

    public String getTime() {
        return this.deadlineDate;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadlineDate + ")";
    }
}
