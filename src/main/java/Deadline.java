public class Deadline extends Task {
    private final String date;

    Deadline(String task, String date) {
        super(task, "deadline");
        this.date = date.replaceFirst("by", "by:");
    }

    @Override
    public String toString() {
        return String.format("[D]%s(%s)", super.toString(), date);
    }
}
