import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

class Event extends Task {
    
    private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-LLL-yyyy");
    private LocalDate eventDate;
    private LocalTime eventTime;

    public Event(String item, LocalDate eventDate, LocalTime eventTime) {
        super(item);
        this.eventDate = eventDate;
        this.eventTime = eventTime;
    }

    public boolean dateCompare(LocalDate date) {
        return this.eventDate.equals(date);
    }

    @Override
    public String getItemAndStatus() {
        String returned = "[E]" + super.getItemAndStatus() + " (at: " + this.eventDate.format(Event.dtf) + " " + this.eventTime + ")";
        return returned;
    }
}