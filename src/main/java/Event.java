import java.time.LocalDate;

public class Event extends Task {
    protected String at;

    Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toMemory() {
        return "E" + super.toMemory() + "@" + this.at;
    }

    @Override
    public boolean isOn(String date) {
        LocalDate time = LocalDate.parse(date);
        LocalDate deadline = LocalDate.parse(this.at);
        return time.equals(deadline);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + Tesseract.formatTime(this.at) + ")";
    }
}
