package duke.info.task;

public class Deadline extends Task {

    private final String date;

    public Deadline(String deadline, String date, boolean isComplete) {
        super(deadline, "deadline", isComplete);
        this.date = date;
    }

    @Override
    public String saveFormat() {
        return super.saveFormat() + "|" + this.date;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.date);
    }
}
