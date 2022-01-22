public class Deadline extends Task {
    private final String endDate;
    private static final String taskType = "D";
    public Deadline(String name, String endDate) {
        super(name);
        this.endDate = endDate;
    }

    public Deadline(String name, String endDate, boolean isDone) {
        super(name, isDone);
        this.endDate = endDate;
    }

    public Deadline markAsDone() {
        return new Deadline(this.name, this.endDate, true);
    }

    public Deadline markAsUndone() {
        return new Deadline(this.name, this.endDate, false);
    }

    public String convertToStoredListFormat() {
        String doneIndicator = "false";
        if (super.isDone) {
            doneIndicator = "true";
        }
        String storedListFormat = String.format("%s|%s|%s|%s", taskType, doneIndicator, super.name, this.endDate);
        return storedListFormat;
    }

    @Override
    public String toString() {
        String doneMark;
        if (super.isDone) {
            doneMark = "X";
        } else {
            doneMark = " ";
        }
        return String.format("[%s][%s] %s (by: %s)",
                taskType, doneMark, super.name, endDate);
    }
}
