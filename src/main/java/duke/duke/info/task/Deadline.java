package duke.info.task;

public class Deadline extends Task {

    public Deadline(String deadline, String date, boolean isComplete) {
        super(deadline, "deadline", isComplete, date);
    }

    @Override
    public String saveFormat() {
        return super.saveFormat() + "|" + this.getDateString();
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.getDateString());
    }
}
