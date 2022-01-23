package dukeClasses;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    private LocalDate deadline;

    public Event(String description, LocalDate deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String identify(){
        String dateString = deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy" ));
        if (super.getIsDone()) {
            return String.format("[E][X] %s (by: %s)", super.getDescription(), dateString);
        } else {
            return String.format("[E][ ] %s (by: %s)", super.getDescription(), dateString);
        }
    }
}
