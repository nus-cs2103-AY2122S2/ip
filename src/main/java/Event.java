import java.time.LocalDate;

public class Event extends Task {

    LocalDate date;

    Event(String details, String date) {
        super(details);
        this.date = LocalDate.parse(date);
    }

    public String symbol() {
        return "E";
    }

    @Override
    public String displayTime() {
        return super.toString() + this.date.getDayOfMonth() + " " + this.date.getMonth() + " " + this.date.getYear();
    }

    @Override
    public String toString() {
        return super.toString() + "/" + this.date.toString();
    }

}
