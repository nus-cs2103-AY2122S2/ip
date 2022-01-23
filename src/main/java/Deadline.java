import java.time.LocalDateTime;

public class Deadline extends Task{

    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, int isDone, LocalDateTime by) {
        super(description, isDone);
        this.by = by;
    }

    public LocalDateTime getBy() {
        return by;
    }

    @Override
    public String toString() {
        int year = by.getYear();
        String month = super.months[by.getMonthValue() - 1];
        int day = by.getDayOfMonth();

        String hour;
        if (by.getHour() < 10) {
            hour = "0" + by.getHour();
        } else {
            hour = String.valueOf(by.getHour());
        }

        String min;
        if (by.getMinute() < 10) {
            min = "0" + by.getMinute();
        } else {
            min = String.valueOf(by.getMinute());
        }

        return "[D]"
                + super.toString()
                + "(by: " + month + " " + day + " " + year + " " + hour + ":" + min + ")";
    }

}