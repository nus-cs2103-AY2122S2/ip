import java.time.LocalDate;

public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toMemory() {
        return "D" + super.toMemory() + "@" + this.by;
    }

    @Override
    public boolean isOn(String date) {
        LocalDate time = LocalDate.parse(date);
        LocalDate deadline = LocalDate.parse(this.by);
        return time.equals(deadline);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + Tesseract.formatTime(this.by) + ")";
    }
}
